package com.example.recyclerview_demo.dataAdapter

import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.recyclerview_demo.R
import com.example.recyclerview_demo.json.DataOutput
import com.example.recyclerview_demo.listener.OnLoadingListener

//https://www.itread01.com/content/1546896990.html
//https://xnfood.com.tw/android-recyclerview2/



/**
 * 步驟二 繼承
 */
class dataAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    /**
     * 步驟三 建立Json格式的class
     */
    private var mdata: MutableList<DataOutput.DepositDetail> = mutableListOf()


    /**
     * 設置監聽
     * 步驟二:建立 參數
     *
     * */
    private var mOnLoadingListener: OnLoadingListener = object : OnLoadingListener {
        override fun onLoading() {
            //default empty listener
        }
    }



    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        /**
         * 這個是RecyclerView的內容物
         * i是用來判斷要用哪個畫面的
         */
        return if (viewType == 1) {//viewType的值會從
            val v = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.content_detail, viewGroup, false)
            ViewHolder(v)
        } else {
            val v = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.content_unsettleup_list_footer, viewGroup, false)
            FooterViewHolder(v)
        }
    }

    /**
     * 這裡也可以設定監聽  然後要判斷物件是綁定哪個畫面的
     * */
    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        try {
            when (viewHolder) {
                is ViewHolder -> {
                    viewHolder.time.text = mdata[position].createTime
                    viewHolder.money.text = mdata[position].amount
                    when (mdata[position].detail) {
                        50 -> viewHolder.detail.text = "利息"
                        51 -> viewHolder.detail.text = "转入"
                        52 -> viewHolder.detail.text = "转出"
                    }
                    /**
                     * 設置監聽
                     * 步驟五:把mOnLoadingListener的事件設定給要觸發的元件，這裡用time當例子
                     *
                     * */
                    viewHolder.time.setOnClickListener { mOnLoadingListener.onLoading() }

                }
                is FooterViewHolder -> {

                }

            }

        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    override fun getItemCount(): Int {
        return mdata.size + 1//在最尾端添加一筆空物件，做 footer view 顯示用
    }

    /**
     * 這裡可以去修改參數來達到切換布局的效果
     * 修改onBindViewHolder的viewHolder參數
     * 跑的順序是
     * getItemViewType -> onCreateViewHolder -> onBindViewHolder
     * getItemViewType回傳的值會變成onCreateViewHolder裡面的viewType參數
     *
     */
    override fun getItemViewType(position: Int): Int {
        return when (position) {
            mdata.lastIndex + 1 -> 0
            else -> 1
        }
    }

    /**
     * 這裡可以給外部用來修改內部的參數
     * notifyDataSetChanged()要加才會更新資料
     * */
    fun setData(mdataList: MutableList<DataOutput.DepositDetail>) {
        //這個是一次更換全部資料
        mdata=mdataList
        notifyDataSetChanged()//更新資料
    }
    fun upData(mdataList: MutableList<DataOutput.DepositDetail>) {
        //這個是添加更多資料
        for(i in 0 until mdataList.size){
            mdata.add(mdataList[i])
        }
        notifyDataSetChanged()//更新資料
    }
    /**
     * 設置監聽
     * 步驟三:這裡是要把Fragment的值塞進來，觸發Fragment給我們的事件
     *
     * */
    fun setOnSelectItemListener(onLoadingListener: OnLoadingListener) {
        mOnLoadingListener = onLoadingListener
    }

    /**
     * 步驟一 建立ViewHolder
     * 這裡的ViewHolder可以建立不同的畫面
     */
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var Lyt: ConstraintLayout = itemView.findViewById(R.id.deposit_detail_CsnLyt)
        internal var time: TextView = itemView.findViewById(R.id.deposit_detail_time)
        internal var money: TextView = itemView.findViewById(R.id.deposit_detail_money)
        internal var detail: TextView = itemView.findViewById(R.id.deposit_detail_detail)
        internal var line: View = itemView.findViewById(R.id.deposit_detail_detail_line)

    }

    inner class FooterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvFooter: TextView = itemView.findViewById(R.id.tv_footer)

    }


}
