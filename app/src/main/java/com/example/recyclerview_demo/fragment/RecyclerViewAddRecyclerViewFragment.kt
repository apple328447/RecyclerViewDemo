package com.example.recyclerview_demo.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.recyclerview_demo.R
import kotlinx.android.synthetic.main.fragment_recycler_view_add_recycler_view.*

/**
 * A simple [Fragment] subclass.
 */
class RecyclerViewAddRecyclerViewFragment : Fragment() {

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

        Log.v("Bill", "onViewCreated")
    }

    private fun initButton() {
        txv_A.isSelected = true
        txv_A.setOnClickListener {
            txv_A.isSelected = true
            txv_B.isSelected = false
            txv_C.isSelected = false
        }
        txv_B.setOnClickListener {
            txv_A.isSelected = false
            txv_B.isSelected = true
            txv_C.isSelected = false
        }
        txv_C.setOnClickListener {
            txv_A.isSelected = false
            txv_B.isSelected = false
            txv_C.isSelected = true
        }
        txv_zoom_bar.setOnClickListener {
            if (content_body.visibility == View.VISIBLE){
                content_title.setBackgroundResource(R.drawable.bg_radius_all_blue)
                content_body.visibility = View.GONE
            }
            else{
                content_title.setBackgroundResource(R.drawable.bg_radius_top_blue)
                content_body.visibility = View.VISIBLE
            }
        }
    }


    init {

        Log.v("Bill", "init")
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
