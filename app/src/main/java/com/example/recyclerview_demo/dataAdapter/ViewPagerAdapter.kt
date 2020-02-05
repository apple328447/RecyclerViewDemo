package com.example.recyclerview_demo.dataAdapter

import android.app.Activity
import android.support.v4.app.FragmentManager
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.example.recyclerview_demo.R

/**
 * @param mActivity :如果底層是
 * Activity:放 context
 * Fragment:放 Activity
 * */
class ViewPagerAdapter(val mActivity: Activity?, val mFragmentManager: FragmentManager?) :
    PagerAdapter() {

    //用來判斷ViewPager要顯示的Style
    internal enum class Type { TYPE_A, TYPE_B, TYPE_C }
    var mCurrentView: Int = 0

    private var mPageList: MutableList<String> = mutableListOf()


//----------------------------Override Function--------------------------------

    /**
     * 實例化 ViewPager 中相對應位置的頁面預設會實例化當前頁面及前後各一個頁面
     * instantiateItem (container: ViewGroup, position: Int) : Object
     * @param container:ViewPager 用來放子項目 View 的容器，這個方法主要就是負責將子項目 View 加進這個 container 中。
     * @param position:要實例化的頁面位置
     * 回傳代表這個子項目 View 的 Object，可以為 View 或這個頁面的其他 container。這個回傳的 Object 會被 isViewFromObject 這個方法拿來做判斷使用，也會被用來做為 destroyItem 方法要移除的 View 的依據。
     */
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        var view = LayoutInflater.from(mActivity).inflate(R.layout.content_viewpager_type1, container, false)//預設
        try {
            view = when (mCurrentView) {
                Type.TYPE_A.ordinal -> LayoutInflater.from(mActivity)
                    .inflate(R.layout.content_viewpager_type1, container, false)
                Type.TYPE_B.ordinal -> LayoutInflater.from(mActivity)
                    .inflate(R.layout.content_viewpager_type2, container, false)
                Type.TYPE_C.ordinal -> LayoutInflater.from(mActivity)
                    .inflate(R.layout.content_viewpager_type3, container, false)
                else -> LayoutInflater.from(mActivity)
                    .inflate(R.layout.content_viewpager_type1, container, false)
            }
            //把view裡面的元件做設定，也可以拿來設定RecyclerView,就是再加一個Adapter，如果Type都不一樣就在上面做判別。
            val txv:TextView=view.findViewById(R.id.txv)
            txv.text=mPageList[position]
            txv.setOnClickListener { Toast.makeText(mActivity,txv.text,Toast.LENGTH_SHORT).show()}
            //將 View 加進 container 中
            container.addView(view)

        }catch (e: Exception) {
            e.printStackTrace()
        }
        return view



    }

    /**
     * 這裡決定你可以翻幾頁
     * */
    override fun getCount(): Int {
        return when(mCurrentView){
            0 -> mPageList.size//回傳A
            1 -> mPageList.size//回傳B
            2 -> mPageList.size//回傳C
            else ->0
        }
    }

    /**
     * PagerAdapter 需要此方法才能正常運作
     * 用來判斷頁面的 View 和上面 instantiateItem 方法回傳的物件是否一樣
     * isViewFromObject (view: View, object: Object) : Boolean
     * @param view:ViewPager 源碼中呼叫此方法的 function 傳入的他們想判斷的 View
     * @param object:instantiateItem 回傳的 Object
    回傳一個 Boolean 值，告訴呼叫此方法的那些 function 他們想判斷的 view 和我們創建的 Object 是不是同一個*/
    override fun isViewFromObject(view: View, obj: Any): Boolean {
        return obj == view
    }

    /**
     *當子項目 View 被滑出預備範圍內（當前及前後各一個頁面）時會被移除
     * destroyItem (container： ViewGroup, position: Int, object: Object)
     * container：要被移除的子項目 View 的 container，這個方法主要就是負責將 View 從 container 中移除。
     * @param position：要移除子項目 View 的頁面位置
     * @param object：代表要移除的子項目 View 的 Object，為 instantiateItem 方法回傳的 Object。
     */
    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    //--------------------------------------------------------------------------
    fun setDataList(pageList:MutableList<String>,type: Int) {
        when (type) {
            0 -> mCurrentView = Type.TYPE_A.ordinal
            1 -> mCurrentView = Type.TYPE_B.ordinal
            2 -> mCurrentView = Type.TYPE_C.ordinal
        }
        mPageList=pageList

        notifyDataSetChanged()

    }
}