package com.example.recyclerview_demo.fragment


import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.example.recyclerview_demo.R
import com.example.recyclerview_demo.dataAdapter.dataAdapter2
import com.example.recyclerview_demo.json.DataOutput
import com.example.recyclerview_demo.listener.OnScrollListener
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.fragment_recycler_view2.*
import java.util.ArrayList

/**Bill
 * Demo RecyclerView中穿插廣告
 *
 * */

class RecyclerView2Fragment : Fragment() {

    private lateinit var mDataArrayList:MutableList<DataOutput.DepositDetail>
    private var mdataDetailAdapter= dataAdapter2()
    private var mGridLayoutManager = GridLayoutManager(activity, 1, LinearLayoutManager.VERTICAL, false)
    private var loading = false //是不是正在加載資料
    private var mNoMoreData = false//True無法再加載
    private var uploadTimes=1

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recycler_view2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initEvent()
        initRecyclerView()
        initRecyclerViewUpdate()
    }
    private fun initEvent(){
        //上拉更新
        swipeRefreshLayout.setOnRefreshListener {
            addDataToRecyclerView()
            Handler().postDelayed({//模拟耗时操作
                run {
                    swipeRefreshLayout.isRefreshing = false//取消刷新
                }
            },500)
        }
    }

    //初始資料
    private fun initRecyclerView() {
        var data="[{\"amount\":\"1\",\"createTime\":\"2019-06-17 17:52:59\",\"detail\":52},{\"amount\":\"2\",\"createTime\":\"2019-06-17 17:52:43\",\"detail\":51},{\"amount\":\"3\",\"createTime\":\"2019-06-17 15:59:23\",\"detail\":51},{\"amount\":\"4\",\"createTime\":\"2019-06-17 15:59:17\",\"detail\":52},{\"amount\":\"5\",\"createTime\":\"2019-06-17 15:58:54\",\"detail\":51},{\"amount\":\"6\",\"createTime\":\"2019-06-17 15:58:30\",\"detail\":52},{\"amount\":\"7\",\"createTime\":\"2019-06-17 15:53:34\",\"detail\":51},{\"amount\":\"8\",\"createTime\":\"2019-06-17 15:53:31\",\"detail\":52},{\"amount\":\"9\",\"createTime\":\"2019-06-17 15:53:28\",\"detail\":51},{\"amount\":\"10\",\"createTime\":\"2019-06-17 15:53:13\",\"detail\":52},{\"amount\":\"11\",\"createTime\":\"2019-06-17 15:53:13\",\"detail\":52}]"
        mDataArrayList= Gson().fromJson(data, object : TypeToken<ArrayList<DataOutput.DepositDetail>>() {}.type)

        mdataDetailAdapter.setData(mDataArrayList)//呼叫Adapter裡面的方法 把資料塞進去
        recyclerView.adapter = mdataDetailAdapter//設定recyclerView的Aapter
        recyclerView.layoutManager = LinearLayoutManager(activity)//設定layoutManager 總共有三種
    }
    //新增資料
    fun addDataToRecyclerView(){
        if(uploadTimes<3){
            loading=true
//            mdataDetailAdapter?.mdata?.addAll(mDataArrayList)
//            mdataDetailAdapter?.notifyDataSetChanged()
            mdataDetailAdapter?.upData(mDataArrayList)//呼叫Adapter裡面的方法 把資料塞進去
            uploadTimes++
        }else{
            mNoMoreData=true//沒有更多資料可以加載
        }
    }
    //資料滑動的時候事件監聽
    private fun initRecyclerViewUpdate() {
        recyclerView.layoutManager = mGridLayoutManager
        recyclerView.adapter = mdataDetailAdapter
        recyclerView.addOnScrollListener(object : OnScrollListener(mGridLayoutManager) {

            override fun loadMoreDate(firstVisibleItem: Int) {
                //前面一筆資料跑完才可以跑第二筆
                if (!loading) {
                    addDataToRecyclerView()
                    recyclerView.scrollToPosition(firstVisibleItem)
                    loading=false
                }
            }

            override fun onNoMore() {
                if (mNoMoreData) {
                    Toast.makeText(activity,"沒有更多資料可以加載", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}
