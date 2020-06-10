package com.example.recyclerview_demo.RecyclerViewAddRecyclerView

import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.LayerDrawable
import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.recyclerview_demo.R
import com.example.recyclerview_demo.utils.ImageUtil
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_recycler_view_add_recycler_view.*

class RecyclerViewAddRecyclerViewFragment : Fragment() {

    private var dataAdapterFirst = dataAdapterFirst(activity)
    var rvDataList: DataOutput = DataOutput()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_recycler_view_add_recycler_view, container, false)
        Log.v("Bill", "onCreateView")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initButton()
        initRecyclerView()


        //Test
        try {
            var drawable =
                ContextCompat.getDrawable(activity!!, R.drawable.bg_bubble_speech_all_radius_blue3)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                var color = Color.parseColor("#BB0B1C")
                var color2 = Color.parseColor("#46BBB5")
                (drawable as LayerDrawable).getDrawable(0)
                    .setColorFilter(color, PorterDuff.Mode.SRC_IN)//邊框染色
                (drawable as LayerDrawable).getDrawable(1)
                    .setColorFilter(color2, PorterDuff.Mode.SRC_IN)//邊框染色
                vg_body_content_layout.setBackgroundDrawable(drawable)

                //=================
                vg_content_layout.background = drawable
            }
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }


//        vg_body_content_layout.setBackgroundDrawable(activity?.let { ImageUtil.getBubbleSpeech(it) })


        Log.v("Bill", "onViewCreated")

    }

    private fun getTypeData(type: Int): MutableList<DataOutput.contentData> {
        var typeData: MutableList<DataOutput.contentData>? = mutableListOf()
        try {
            if (rvDataList != null) {
                var dataList = rvDataList.dataList?.firstOrNull {
                    it.type == type
                } as DataOutput.contentTypeData

                typeData = dataList?.content!!
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return typeData!!
    }

    private fun initRecyclerView() {
        rvDataList = Gson().fromJson(
            "{\"dataList\":[{\"id\":30575414,\"fk\":null,\"type\":\"5\",\"content\":[{\"name\":\"A\",\"img\":[1,2,3,4,5]},{\"name\":\"B\",\"img\":[2,3,4,5,1]},{\"name\":\"C\",\"img\":[3,4,5,1,2]},{\"name\":\"D\",\"img\":[4,5,1,2,3]},{\"name\":\"E\",\"img\":[5,4,3,2,1]}]},{\"id\":30575414,\"fk\":null,\"type\":\"6\",\"content\":[{\"name\":\"A\",\"img\":[1,2,3,4,5,6]},{\"name\":\"B\",\"img\":[2,3,4,5,6,1]}]},{\"id\":30575414,\"fk\":null,\"type\":\"7\",\"content\":[{\"name\":\"A\",\"img\":[1,2,3,4,5,6,7]},{\"name\":\"B\",\"img\":[2,3,4,5,6,7,1]}]},{\"id\":30575414,\"fk\":null,\"type\":\"8\",\"content\":[{\"name\":\"A\",\"img\":[1,2,3,4,5,6,7,8]},{\"name\":\"B\",\"img\":[2,3,4,5,6,7,8,1]}]}]}",
            DataOutput::class.java
        )
        recyclerView.adapter = dataAdapterFirst
        recyclerView.layoutManager = LinearLayoutManager(activity)
        txv_A.performClick()
    }


    private fun initButton() {
        txv_A.isSelected = true
        txv_A.setOnClickListener {
            txv_A.isSelected = true
            txv_B.isSelected = false
            txv_C.isSelected = false
            txv_D.isSelected = false
            dataAdapterFirst.setData(getTypeData(5))
        }
        txv_B.setOnClickListener {
            txv_A.isSelected = false
            txv_B.isSelected = true
            txv_C.isSelected = false
            txv_D.isSelected = false
            dataAdapterFirst.setData(getTypeData(6))
        }
        txv_C.setOnClickListener {
            txv_A.isSelected = false
            txv_B.isSelected = false
            txv_C.isSelected = true
            txv_D.isSelected = false
            dataAdapterFirst.setData(getTypeData(7))
        }
        txv_D.setOnClickListener {
            txv_A.isSelected = false
            txv_B.isSelected = false
            txv_C.isSelected = false
            txv_D.isSelected = true
            dataAdapterFirst.setData(getTypeData(8))
        }
        txv_zoom_bar.setOnClickListener {
            if (content_body.visibility == View.VISIBLE) {
                content_title.setBackgroundResource(R.drawable.bg_radius_all_blue)
                content_body.visibility = View.GONE
            } else {
                content_title.setBackgroundResource(R.drawable.bg_bubble_speech_top_blue)
                content_body.visibility = View.VISIBLE
            }
        }
    }

    override fun onResume() {
        super.onResume()
        Log.v("Bill", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.v("Bill", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.v("Bill", "onStop")
    }
}
