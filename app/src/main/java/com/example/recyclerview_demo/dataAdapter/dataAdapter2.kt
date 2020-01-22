package com.example.recyclerview_demo.dataAdapter

import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.recyclerview_demo.R
import com.example.recyclerview_demo.json.DataOutput

class dataAdapter2 : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    internal enum class Type { View1, View2, FOOTER_VIEW }

    private var mDataList: MutableList<DataOutput.DepositDetail> = mutableListOf()
    private lateinit var layoutView: View

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return when (viewType) {
            Type.View1.ordinal -> {
                layoutView = LayoutInflater.from(viewGroup.context).inflate(R.layout.content_detail, viewGroup, false)
                DetailViewHolder(layoutView)
            }
            Type.View2.ordinal -> {
                layoutView = LayoutInflater.from(viewGroup.context)
                    .inflate(R.layout.content_advertisement_body, viewGroup, false)
                AdViewHolder(layoutView)
            }
            else -> {
                layoutView = LayoutInflater.from(viewGroup.context)
                    .inflate(R.layout.content_unsettleup_list_footer, viewGroup, false)
                FooterViewHolder(layoutView)
            }
        }
    }

    override fun getItemCount(): Int {
        return mDataList.size + 1 //在最尾端添加一筆空物件，做 footer view 顯示用
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        try {
            when(viewHolder){
                is DetailViewHolder ->{
                    viewHolder.time.text = mDataList[position].createTime
                    viewHolder.money.text = mDataList[position].amount
                    when (mDataList[position].detail) {
                        50 -> viewHolder.detail.text = "利息"
                        51 -> viewHolder.detail.text = "转入"
                        52 -> viewHolder.detail.text = "转出"
                    }
                }
                is AdViewHolder->{
                    Log.v("Bill","onBindViewHolder ========> AdViewHolder")
                }
                is FooterViewHolder->{
                    Log.v("Bill","onBindViewHolder ========> FooterViewHolder")
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            mDataList.lastIndex + 1 -> Type.FOOTER_VIEW.ordinal
            2, 8, 12 -> Type.View2.ordinal
            else -> Type.View1.ordinal
        }
    }

    /**
     * 這裡可以給外部用來修改內部的參數
     * notifyDataSetChanged()要加才會更新資料
     * */
    fun setData(mdataList: MutableList<DataOutput.DepositDetail>) {
        //這個是一次更換全部資料
        mDataList = mdataList
        notifyDataSetChanged()//更新資料
    }

    fun upData(mdataList: MutableList<DataOutput.DepositDetail>) {
        //這個是添加更多資料
        for (i in 0 until mdataList.size) {
            mDataList.add(mdataList[i])
        }
        val index = if (mDataList.lastIndex - 1 < 0) 0 else mDataList.lastIndex
        notifyItemInserted(index)//更新資料
        //notifyDataSetChanged()//全部更新資料
    }

    /**
     * 步驟一 建立ViewHolder
     * 這裡的ViewHolder可以建立不同的畫面
     */
    class DetailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var Lyt: ConstraintLayout = itemView.findViewById(R.id.deposit_detail_CsnLyt)
        internal var time: TextView = itemView.findViewById(R.id.deposit_detail_time)
        internal var money: TextView = itemView.findViewById(R.id.deposit_detail_money)
        internal var detail: TextView = itemView.findViewById(R.id.deposit_detail_detail)
        internal var line: View = itemView.findViewById(R.id.deposit_detail_detail_line)
    }

    class AdViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgAD: ImageView = itemView.findViewById(R.id.img_adview)
    }

    class FooterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvFooter: TextView = itemView.findViewById(R.id.tv_footer)
    }

}