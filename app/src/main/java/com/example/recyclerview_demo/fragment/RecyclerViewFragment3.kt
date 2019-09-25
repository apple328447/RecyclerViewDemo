package com.example.recyclerview_demo.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.example.recyclerview_demo.R
import com.example.recyclerview_demo.dataAdapter.MarqueenAdapter
import com.example.recyclerview_demo.listener.OnSelectItemListener
import com.example.recyclerview_demo.views.CustomLinearLayoutManager
import kotlinx.android.synthetic.main.fragment_recycler_view3.*


/**Bill
 * Demo RecyclerView的基本用法以及
 * 上拉加載的邏輯是：監聽是否有往上拉>如果有就加載資料
 * 缺點:1.感覺會卡卡的，而且一開始載入的資料太少會無法"往上拉"就沒辦法更新
 *     2.每次都要抓更新前在畫面顯示中的第一筆資料，用在加載完之後手動設定初始位置(才不會有LAG加載的感覺，跳格，沒有滑順感)
 **/

class RecyclerViewFragment3 : Fragment() {


    val titleList: MutableList<String> = mutableListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recycler_view3, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initEvent()
        initRecyclerView()
    }
    private fun initEvent(){
    }

    //初始資料
    private fun initRecyclerView() {
        //TODO Bill Test
        setDataToMarqueen()

        var mDataDetailAdapter= MarqueenAdapter()
        mDataDetailAdapter.setData(titleList)
        mDataDetailAdapter.setOnSelectItemListener(object: OnSelectItemListener<String> {
            override fun onClick(select: String) {
                try {
                    showAnnouncementMessageDialog(select.toInt())
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        })
        recyclerView.adapter = mDataDetailAdapter

        if(context!=null){
            var customLinearLayoutManager = CustomLinearLayoutManager(context!!,LinearLayoutManager.HORIZONTAL, false)
            customLinearLayoutManager.setScrollEnabled(true)
            recyclerView.layoutManager =customLinearLayoutManager
        }

    }

    //公告跑馬燈訊息彈窗 //selectPage: 初始顯示頁面
    private fun showAnnouncementMessageDialog(selectPage: Int) {
        try {
            if (titleList.size > 0)
                Toast.makeText(context!!,"你點選了第$selectPage 資料",Toast.LENGTH_SHORT).show()

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    //給公告值
    private fun setDataToMarqueen(){
        for (i in 0..5) {
            titleList.add("這個是公告$i:這個是第$i 筆資料")
        }
    }

    override fun onResume() {
        super.onResume()
        recyclerView.startAuto()
    }

    override fun onStop() {
        super.onStop()
        recyclerView.stopAuto()
    }

}
