package com.example.recyclerview_demo.listener

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView

abstract class OnScrollListener(private val mGridLayoutManager: GridLayoutManager) : RecyclerView.OnScrollListener() {


    //已經加載出來的Item的數量
    private var totalItemCount: Int = 0

    //主要用來存儲上一個totalItemCount
    private var previousTotal = 0

    //在屏幕上可見的item數量
    private var visibleItemCount: Int = 0

    //在屏幕可見的Item中的第一個
    private var firstVisibleItem: Int = 0

    //是否正在callAPI
    private var loading = false

    //true:往下滑
    private var isSlidingToLast = false


    /**
     * SCROLL_STATE_IDLE = 0;（静止没有滚动）
     * SCROLL_STATE_DRAGGING = 1;（正在被外部拖拽,一般为用户正在用手指滚动）
     * SCROLL_STATE_SETTLING = 2;（自动滚动）
     * */
    //滚动状态变化时回调
    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)

        // 當不滾動時
        if (newState == RecyclerView.SCROLL_STATE_IDLE) {
            val lastVisibleItem = mGridLayoutManager.findLastCompletelyVisibleItemPosition()
            val totalItemCount = mGridLayoutManager.itemCount

            if (lastVisibleItem == totalItemCount - 1 && isSlidingToLast) {
                isSlidingToLast = false//沒設回來會一直顯示true
                onNoMore()
            }
        }
    }
    /**
     * dx > 0 时为手指向左滚动,列表滚动显示右面的内容
     * dx < 0 时为手指向右滚动,列表滚动显示左面的内容
     * dy > 0 时为手指向上滚动,列表滚动显示下面的内容
     * dy < 0 时为手指向下滚动,列表滚动显示上面的内容
     * */
    //滚动时回调
    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)


        isSlidingToLast = dy > 0
        visibleItemCount = mGridLayoutManager.childCount
        totalItemCount = mGridLayoutManager.itemCount
        firstVisibleItem = mGridLayoutManager.findFirstVisibleItemPosition()
        if (dy > 0) {
            //判斷是否滑底 開始加載資料
            if (!loading && (visibleItemCount + firstVisibleItem) >= totalItemCount-1) {
                loading = true
                loadMoreDate(firstVisibleItem)
            } else if (loading) {
                //當目前總資料>先前總資料 則判斷為加載結束
                if (totalItemCount > previousTotal) {
                    //數據已經加載結束
                    loading = false
                    previousTotal = totalItemCount
                }
            }
        }
    }

    /**
     * 提供方法，在Activity中監聽到這個EndLessOnScrollListener
     */
    abstract fun loadMoreDate(firstVisibleItem: Int)

    abstract fun onNoMore()
}