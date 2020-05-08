package com.example.recyclerview_demo.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.animation.LinearInterpolator;
import com.example.recyclerview_demo.R;

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

 /**
 * 設定跑馬燈速度 app:period=" "
 * 設定延遲      app:delay=" "
 **/



 /**
  * 构造函数参数最多有四个。简单总结下：
  *
  * Context - View中随处都会用到。
  *
  * AttributeSet - XML属性（当从XML inflate的时候）。
  *
  * int defStyleAttr - 应用到View的默认风格（定义在主题中）。
  *
  * int defStyleResource - 如果没有使用defStyleAttr，应用到View的默认风格
  *
  * 除了Context，其它的参数只是用来通过XML属性配置View的初始状态（从布局，style以及theme中）。
  * */
public class AutoScrollRecyclerView extends RecyclerView {

     int delay = 0;
     int period = 250;

     // 如果View是在Java代码里面new的，则调用第一个构造函数
    public AutoScrollRecyclerView(Context context) {
        this(context,null);
    }
     // 如果View是在.xml里声明的，则调用第二个构造函数
     // 自定义属性是从AttributeSet参数传进来的
    public AutoScrollRecyclerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
        initData(attrs);
    }

     // 不会自动调用
     // 一般是在第二个构造函数里主动调用
     // 如View有style属性时
    public AutoScrollRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initData(attrs);
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
        }, 0, 1000); //在 0 秒後，每隔 250L 毫秒執行一次
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

    /**
     * 使用自訂變數app:的方式
     * 1.去res>values>attrs建 name要和class名稱一樣
     * 2.Xml設定變數
     * 3.來自定義的View去設參數
     * */
    public void initData(AttributeSet attrs) {
        TypedArray typedArray = this.getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.AutoScrollRecyclerView, 0, 0);
        try {
            if (typedArray.getString(R.styleable.AutoScrollRecyclerView_delay) != null) {
                delay = Integer.parseInt(typedArray.getString(R.styleable.AutoScrollRecyclerView_delay));
            }
            if (typedArray.getString(R.styleable.AutoScrollRecyclerView_period) != null) {
                period = Integer.parseInt(typedArray.getString(R.styleable.AutoScrollRecyclerView_period));
            }
        } finally {
            typedArray.recycle();
        }
    }
}