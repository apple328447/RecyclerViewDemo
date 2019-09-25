package com.example.recyclerview_demo.dataAdapter

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.recyclerview_demo.R
import com.example.recyclerview_demo.listener.OnSelectItemListener


/**
 * 2019/8/26 Created by Bill
 * 這個範例是使用RecylerView做成跑馬燈的形式，目前網路上找的Marqueen套件沒辦法滿足這個需求(最接近的會有跳頁動畫)
 * 1.資料要橫著顯示，看起來要連著，而且不能斷掉
 * 2.點選的時候要有跳窗顯示完整事件
 * */

class MarqueenAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>()  {

    private var mDataList: MutableList<String> = mutableListOf()
    private lateinit var layoutView: View

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        layoutView = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.content_marqueen, viewGroup, false)
        return DetailViewHolder(layoutView)
    }

    //點擊詳情按鈕，返回要顯示的 info 文字
    private var mOnSelectItemListener: OnSelectItemListener<String> = object : OnSelectItemListener<String> {
        override fun onClick(message: String) {
            //default empty listener
        }
    }

    override fun getItemCount(): Int {
        //return mDataList.size
        /**
         * 重點一
         * */
        return Integer.MAX_VALUE
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        when (viewHolder) {
            is DetailViewHolder -> {
                /**
                 * 重點二
                 * */
                viewHolder.detail.text=mDataList[position%mDataList.size]+"        "
                viewHolder.detail.setOnClickListener {
                    Log.v("Bill","=============被點選:$position==========")
                    mOnSelectItemListener.onClick((position%mDataList.size).toString())
                }
            }
        }
    }

    fun setOnSelectItemListener(onSelectItemListener: OnSelectItemListener<String>) {
        mOnSelectItemListener = onSelectItemListener
    }

    fun setData(mdataList: MutableList<String>) {
        mDataList = mdataList
        notifyDataSetChanged()//更新資料
    }

    class DetailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var detail: TextView = itemView.findViewById(R.id.tv_marqueen)
    }




}