package com.example.recyclerview_demo.fragment


import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.example.recyclerview_demo.R
import com.example.recyclerview_demo.dataAdapter.dataAdapter
import com.example.recyclerview_demo.json.DataOutput
import com.example.recyclerview_demo.listener.OnLoadingListener
import com.example.recyclerview_demo.listener.OnScrollListener
import com.example.recyclerview_demo.utils.TimeUtil
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.fragment_recycler_view.*
import java.util.*

/**Bill
 * Demo RecyclerView的基本用法以及
 * 上拉加載的邏輯是：監聽是否有往上拉>如果有就加載資料
 * 缺點:1.感覺會卡卡的，而且一開始載入的資料太少會無法"往上拉"就沒辦法更新
 *     2.每次都要抓更新前在畫面顯示中的第一筆資料，用在加載完之後手動設定初始位置(才不會有LAG加載的感覺，跳格，沒有滑順感)
 **/

class RecyclerViewFragment : Fragment() {


    private lateinit var mDataArrayList: MutableList<DataOutput.DepositDetail>

    //    private  var mdataDetailAdapter: dataAdapter?=null//改成下面 有"()才代表有new過"
    private var mdataDetailAdapter = dataAdapter(activity)

    //兩種風格
    private var mGridLayoutManager =
        GridLayoutManager(activity, 1, LinearLayoutManager.VERTICAL, false)//GridLayout
    private var linearLayoutManager =
        LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)//LinearLayout

    private var loading = false //是不是正在加載資料
    private var mNoMoreData = false//True無法再加載
    private var uploadTimes = 1


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recycler_view, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initEvent()
        initRecyclerView()
        initRecyclerViewUpdate()

    }

    private fun initEvent() {
        //上拉更新
        swipeRefreshLayout.setOnRefreshListener {
            addDataToRecyclerView()
            Handler().postDelayed({
                //模拟耗时操作
                run {
                    swipeRefreshLayout.isRefreshing = false//取消刷新
                }
            }, 500)
        }
    }

    //初始資料
    private fun initRecyclerView() {
        var data =
            "[{\"amount\":\"1\",\"createTime\":\"2019-06-17 17:52:59\",\"detail\":52},{\"amount\":\"2\",\"createTime\":\"2019-06-17 17:52:43\",\"detail\":51},{\"amount\":\"3\",\"createTime\":\"2019-06-17 15:59:23\",\"detail\":51},{\"amount\":\"4\",\"createTime\":\"2019-06-17 15:59:17\",\"detail\":52},{\"amount\":\"5\",\"createTime\":\"2019-06-17 15:58:54\",\"detail\":51},{\"amount\":\"6\",\"createTime\":\"2019-06-17 15:58:30\",\"detail\":52},{\"amount\":\"7\",\"createTime\":\"2019-06-17 15:53:34\",\"detail\":51},{\"amount\":\"8\",\"createTime\":\"2019-06-17 15:53:31\",\"detail\":52},{\"amount\":\"9\",\"createTime\":\"2019-06-17 15:53:28\",\"detail\":51},{\"amount\":\"10\",\"createTime\":\"2019-06-17 15:53:13\",\"detail\":52},{\"amount\":\"11\",\"createTime\":\"2019-06-17 15:53:13\",\"detail\":52}]"
        mDataArrayList = Gson().fromJson(
            data,
            object : TypeToken<java.util.ArrayList<DataOutput.DepositDetail>>() {}.type
        )

//        mDataArrayList= Gson().fromJson(data, DataOutput.DepositDetail::class.java)//這個方式是用來包非陣列的型態

        mdataDetailAdapter.setData(mDataArrayList)//呼叫Adapter裡面的方法 把資料塞進去
        /**
         * 設置監聽
         * 步驟四:這裡是要把Fragment的值塞進來
         * ＊＊＊call back＊＊＊
         * */
        mdataDetailAdapter.setOnSelectItemListener(object : OnLoadingListener {
            override fun onLoading() {
                //這裡放你想要觸發的效果
                Toast.makeText(activity, "觸發事件", Toast.LENGTH_SHORT).show()
            }
        })
        recyclerView.adapter = mdataDetailAdapter//設定recyclerView的Aapter
        recyclerView.layoutManager = LinearLayoutManager(activity)//設定layoutManager 總共有三種
    }

    //新增資料
    fun addDataToRecyclerView() {
        if (uploadTimes < 3) {
            loading = true
//            mdataDetailAdapter?.mdata?.addAll(mDataArrayList)
//            mdataDetailAdapter?.notifyDataSetChanged()
            mdataDetailAdapter?.upData(mDataArrayList)//呼叫Adapter裡面的方法 把資料塞進去
            uploadTimes++
        } else {
            mNoMoreData = true//沒有更多資料可以加載
        }
    }

    //資料滑動的時候事件監聽
    private fun initRecyclerViewUpdate() {
        recyclerView.layoutManager = mGridLayoutManager
        recyclerView.adapter = mdataDetailAdapter

        /**
         * 這裡有兩種上拉加載的方式
         * 方法一：用起來比較順
         * 方法二：寫起來比較簡單
         * */
        //方法一：寫一個自定義的OnScrollListener
        recyclerView.addOnScrollListener(object : OnScrollListener(mGridLayoutManager) {

            override fun loadMoreDate(firstVisibleItem: Int) {
                //前面一筆資料跑完才可以跑第二筆
                if (!loading) {
                    addDataToRecyclerView()
                    recyclerView.scrollToPosition(firstVisibleItem)
                    loading = false
                }
            }

            override fun onNoMore() {
                if (mNoMoreData) {
                    Toast.makeText(activity, "沒有更多資料可以加載", Toast.LENGTH_SHORT).show()
                }
            }
        })

        //方法二：直接用原生的
//        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                super.onScrolled(recyclerView, dx, dy)
//            }
//
//            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
//                // RecyclerView.canScrollVertically(1) 的值表示是否能向上滚动，false表示已经滚动到底部
//                // RecyclerView.canScrollVertically(-1) 的值表示是否能向下滚动，false表示已经滚动到顶部
//                if (!loading) {//前面的資料撈完才能撈下一筆
//                    if (!mNoMoreData && !recyclerView.canScrollVertically(1)) {//如果還有資料可以加載&&往下拉到底部
//                        addDataToRecyclerView()
//                        loading = false
//                    }
//                }
//
//            }
//        })
    }
}
//TODO Bill 教學 待整理 日期的加減
//var ccc= Calendar.getInstance().add(Calendar.DATE,-1) //這個不知道加剪完後要怎麼處理
//var ccca= TimeUtil.timeFormat(System.currentTimeMillis() - 60 * 60 * 24 * 1000, "yyyy-MM-dd")//當前時間剪一天(用毫秒去算)
//var cccaaa=Calendar.getInstance().timeInMillis 這兩個都是取當前時間 System.currentTimeMillis()
