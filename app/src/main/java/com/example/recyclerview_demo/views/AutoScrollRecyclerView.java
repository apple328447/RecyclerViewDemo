package com.example.recyclerview_demo.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.animation.LinearInterpolator;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 自動滑動 RecycleView
 *
 * 使用方法:
 * 在 onResume() 呼叫 startAuto()
 * 在 onPause() 呼叫 stopAuto()
 *
 * created by Simon Chang
 */
public class AutoScrollRecyclerView extends RecyclerView {

    public AutoScrollRecyclerView(Context context) {
        this(context,null);
    }

    public AutoScrollRecyclerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public AutoScrollRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    private Timer mTimer;
    public void startAuto() {
        stopAuto();

        mTimer = new Timer();

        //每 250L 毫秒執行一次 smoothScrollBy()
        //紀錄: smoothScrollBy() 的時間間隔為 250L，所以 period 設 250L 達到平順滑動的感覺
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                int distance = dpToPx(getContext(), 10);
                smoothScrollBy(distance, distance, new LinearInterpolator());
            }
        }, 0, 250); //在 0 秒後，每隔 250L 毫秒執行一次
    }

    public void stopAuto() {
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }
    }

    //判断是否拦截事件
    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        Log.v("Bill","=============AutoScrollRecyclerView:onInterceptTouchEvent");
        return false;
    }


//    @Override
//    public boolean onInterceptTouchEvent(MotionEvent e) {
//        Log.v("Bill",e.toString());
//        return super.onInterceptTouchEvent(e);
//    }

    public static int dpToPx(Context context, float valueInDp) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, valueInDp, metrics);
    }
}