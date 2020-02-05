package com.example.recyclerview_demo.fragment


import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.recyclerview_demo.R
import com.example.recyclerview_demo.dataAdapter.ViewPagerAdapter
import kotlinx.android.synthetic.main.fragment_view_pager.*

/**
 * A simple [Fragment] subclass.
 */
class ViewPagerFragment : Fragment() {

    private lateinit var mViewPagerAdapter: ViewPagerAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_pager, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initTabListeners()
        refreshPageView()

    }

    //ViewPager 與 TabLayout 聯動設定
    private fun initTabListeners() {
        tabLayout.addOnTabSelectedListener(object :
            TabLayout.ViewPagerOnTabSelectedListener(viewPager) {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                super.onTabSelected(tab)
            }
        })
        viewPager.addOnPageChangeListener(object :
            TabLayout.TabLayoutOnPageChangeListener(tabLayout) {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
            }
        })
    }

    private fun refreshTabLayout() {
        //先清空 tabLayout 裡面的元素後，再添加
        tabLayout.removeAllTabs()
        //添加Tab
        tabLayout.addTab(tabLayout.newTab().setText("Tab 1"))
        tabLayout.addTab(tabLayout.newTab().setText("Tab 2"))
        tabLayout.addTab(tabLayout.newTab().setText("Tab 3"))
    }

    private fun refreshPageView() {

        refreshTabLayout()
        mViewPagerAdapter = ViewPagerAdapter(activity, fragmentManager)
        var dataList: MutableList<String> = mutableListOf()
        dataList.add("ViewPager A")
        dataList.add("ViewPager B")
        dataList.add("ViewPager C")
        mViewPagerAdapter.setDataList(dataList,0)

        viewPager.adapter = mViewPagerAdapter
    }


}
