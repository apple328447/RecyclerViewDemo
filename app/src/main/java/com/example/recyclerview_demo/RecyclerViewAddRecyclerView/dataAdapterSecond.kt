package com.example.recyclerview_demo.RecyclerViewAddRecyclerView

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.recyclerview_demo.R
import com.example.recyclerview_demo.listener.OnSelectItemListener

class dataAdapterSecond(private val mContext: Context?):RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private var imgDataList: MutableList<Int> = mutableListOf()

    private var mOnSelectItemListener: OnSelectItemListener<String> = object : OnSelectItemListener<String> {
        override fun onClick(message: String) {
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(LayoutInflater.from(viewGroup.context).inflate(R.layout.content_second_layer, viewGroup, false))
    }

    override fun getItemCount(): Int {
        return imgDataList.size
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        try {
            when (viewHolder) {
                is ViewHolder -> {
                    viewHolder.imgNumber.setImageResource(getBallNumberRes(imgDataList[position]))
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun setData(mdataList: MutableList<Int>?) {
        imgDataList = mdataList ?: mutableListOf()
        notifyDataSetChanged()//更新資料
    }

    fun getBallNumberRes(number: Int): Int {
        return when (number) {
            1 -> R.drawable.number_1
            2 -> R.drawable.number_2
            3 -> R.drawable.number_3
            4 -> R.drawable.number_4
            5 -> R.drawable.number_5
            6 -> R.drawable.number_6
            7 -> R.drawable.number_7
            8 -> R.drawable.number_8
            else -> 0
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgNumber: ImageView = itemView.findViewById(R.id.img_title)
    }
}