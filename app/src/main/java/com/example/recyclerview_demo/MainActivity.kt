package com.example.recyclerview_demo

import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat
import android.view.View
import com.example.recyclerview_demo.fragment.RecyclerViewFragment
import android.view.View.SYSTEM_UI_FLAG_FULLSCREEN
import android.view.Window
import android.view.WindowManager
import com.example.recyclerview_demo.fragment.RecyclerView2Fragment
import com.example.recyclerview_demo.fragment.RecyclerViewFragment3


class MainActivity : AppCompatActivity() {

    private var mCurrentFragment: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        goToFragment(RecyclerViewFragment(),"RecyclerViewFragment")
//        goToFragment(RecyclerView2Fragment(),"RecyclerView2Fragment")
        goToFragment(RecyclerViewFragment3(),"RecyclerView3Fragment")


        //隱藏StatusBar
        //hideStatusBar()
    }

    /**
     * 導覽列 頁面切換
     * @param changeToFragment: 要跳轉的 fragment
     */
    private fun goToFragment(changeToFragment: Fragment?, tag: String) {

        if (changeToFragment == null) return
        if (mCurrentFragment === changeToFragment) { //紀錄: "==" 判斷值是否相等， "===" 判斷是否為相同對象(Object)
            //EventBus.getDefault().post(GoToPageEvent(GoToPageEvent.Page.NavGame_Home)) //回到首頁
            return
        }
        val ft = supportFragmentManager.beginTransaction()


        if (mCurrentFragment == null) { //首次添加 fragment
            ft.add(R.id.Deposit_Fragment_FrameLayout, changeToFragment, tag)

        } else if (!changeToFragment.isAdded) { // 先判断是否被add过
            ft.hide(mCurrentFragment!!).add(R.id.Deposit_Fragment_FrameLayout, changeToFragment, tag) // 隐藏当前的fragment，add下一个到Activity中

        } else {
            ft.hide(mCurrentFragment!!).show(changeToFragment) // 隐藏当前的fragment，显示下一个
        }

        mCurrentFragment = changeToFragment
        if(tag!="DepositMainFragment"){
            ft.addToBackStack(null)
        }
        ft.commitAllowingStateLoss()
    }

    /**
     * 隱藏StatusBar
     * */
    private fun hideStatusBar(){
        if (Build.VERSION.SDK_INT >= 16) {
            window.setFlags(AccessibilityNodeInfoCompat.ACTION_NEXT_HTML_ELEMENT, AccessibilityNodeInfoCompat.ACTION_NEXT_HTML_ELEMENT)
            window.decorView.systemUiVisibility = 3328
        }else{
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)
        }
    }
}
