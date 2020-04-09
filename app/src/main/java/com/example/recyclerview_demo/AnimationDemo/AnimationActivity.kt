package com.example.recyclerview_demo.AnimationDemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import com.example.recyclerview_demo.R
import kotlinx.android.synthetic.main.activity_animation.*

class AnimationActivity : AppCompatActivity() {

    var lastX:Float?=null
    var lastY:Float?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animation)
        createImgView()
    }

    private fun initView() {
        iv.x = 100F
        iv.y = 100F
    }


    fun initEvent() {
//        iv.setOnTouchListener(object : View.OnTouchListener {
//            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
//                when(event?.action){
//                    MotionEvent.ACTION_DOWN -> {
//                        var lastX = event.rawX
//                        lastY = event.rawY
//                        return true
//                    }//Do Something
//                    MotionEvent.ACTION_MOVE->{
//                        var distanceX= lastX!!.minus(event.rawX)
//                        var distanceY= lastY!!.minus(event.rawY)
//
//                        var nextX=iv.x.minus(distanceX)
//                        var nextY=iv.y.minus(distanceY)
//
//                        // 不能移出螢幕
//                        if (nextY < 0) {
//                            nextY = 0
//                        } else if (nextY > containerHeight - iv.getHeight()) {
//                            nextY = containerHeight - iv.getHeight();
//                        }
//                    }
//
//                }
//                return v?.onTouchEvent(event) ?: true
//            }
//        }
//        )
    }

//動態添加i
    fun createImgView(){
        var img= ImageView(applicationContext)
//        img.setImageDrawable(R.mipmap.ic_launcher_round)
        img.setImageResource(R.mipmap.ic_launcher_round)
        img.x=200F
        img.y=600F
        var mContent = ConstraintLayout.LayoutParams(WRAP_CONTENT,WRAP_CONTENT)

        content.addView(img,mContent)

    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
    }
}
