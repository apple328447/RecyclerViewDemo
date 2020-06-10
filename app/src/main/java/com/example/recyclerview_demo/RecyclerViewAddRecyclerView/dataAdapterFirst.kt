package com.example.recyclerview_demo.RecyclerViewAddRecyclerView

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.recyclerview_demo.R
import com.example.recyclerview_demo.listener.OnSelectItemListener

class dataAdapterFirst(private val mContext: Context?) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var dataList: MutableList<DataOutput.contentData> = mutableListOf()

    //private var dataAdapterFirst = dataAdapterSecond(mContext)

    private var mOnBtnClickListener: OnBtnClickListener<String> =
        object : OnBtnClickListener<String> {
            override fun onBtnAClick(select: String) {
                TODO("Not yet implemented")
            }

            override fun onBtnBClick(select: String) {
                TODO("Not yet implemented")
            }

            override fun onBtnCClick(select: String) {
                TODO("Not yet implemented")
            }
        }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.content_first_layer, viewGroup, false)
        )
//        addView        (LayoutInflater.from(context).inflate(R.layout.view_deposit_info, this, false))
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        try {
            when (viewHolder) {
                is ViewHolder -> {
                    //Title
                    viewHolder.tvTitle.text = dataList[position].name
                    //數字部分
                    var dataAdapterFirst = dataAdapterSecond(mContext)
                    //TODO BIll RecyclerView 中再塞一層 RecyclerView
                    viewHolder.rvSecond.adapter = dataAdapterFirst
                    viewHolder.rvSecond.layoutManager =  LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false)
                    dataAdapterFirst.setData(dataList[position].img)
                    //按鈕
//                    viewHolder.btnA.setOnClickListener { mOnBtnClickListener.onBtnAClick("") }
//                    viewHolder.btnB.setOnClickListener { mOnBtnClickListener.onBtnAClick("") }
//                    viewHolder.btnC.setOnClickListener { mOnBtnClickListener.onBtnAClick("") }

                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun setData(mdataList: MutableList<DataOutput.contentData>) {
        dataList = mdataList
        notifyDataSetChanged()//更新資料
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvTitle: TextView = itemView.findViewById(R.id.name)
        var rvSecond: RecyclerView = itemView.findViewById(R.id.recyclerView)
        var btnA:Button=itemView.findViewById(R.id.btn_A)
        var btnB:Button=itemView.findViewById(R.id.btn_B)
        var btnC:Button=itemView.findViewById(R.id.btn_C)
    }

    fun setOnBtnListener(onBtnClickListener: OnBtnClickListener<String>) {
        mOnBtnClickListener = onBtnClickListener
    }

    interface OnBtnClickListener<T> {
        fun onBtnAClick(select: T)
        fun onBtnBClick(select: T)
        fun onBtnCClick(select: T)
    }
}