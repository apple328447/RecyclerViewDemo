package com.example.recyclerview_demo.views

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View

/**
 * 2019/8/26 Created by Bill
 * 這邊是去控制使用者能不能滑動RecyclerView的設定
 * 但是會有一個情況產生就是:如果禁止滑動的話，就無法使用startAuto()的方法
 * 解決方法:使用onInterceptTouchEvent 回傳false 擋住RecyclerView的那一層讓他跳過
 * 直接進入Adapter的Listener去觸發事件(這樣也可以避免使用者要連點兩次才會觸發Listener)
 * */

class CustomLinearLayoutManager(context: Context,orientation:Int,reverseLayout:Boolean) : LinearLayoutManager(context,  orientation,  reverseLayout) {
    private var isScrollEnabled = true

    fun setScrollEnabled(flag: Boolean) {
        this.isScrollEnabled = flag
    }

    override fun canScrollVertically(): Boolean {
        return isScrollEnabled && super.canScrollVertically()
    }

    override fun canScrollHorizontally(): Boolean {
        return isScrollEnabled && super.canScrollHorizontally()
    }

    override fun onInterceptFocusSearch(focused: View, direction: Int): View? {
        Log.v("Bill","===============CustomLinearLayoutManager:onInterceptFocusSearch:$direction")
        return super.onInterceptFocusSearch(focused, direction)
    }
}
