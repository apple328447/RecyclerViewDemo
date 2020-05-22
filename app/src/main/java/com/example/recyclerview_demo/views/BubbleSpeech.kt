package com.example.recyclerview_demo.views

import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.widget.LinearLayout
import com.example.recyclerview_demo.R

/**
 * @author Bill
 * 新聊天室氣泡對話框
 * */

class BubbleSpeech(context: Context?, attrs: AttributeSet?) : LinearLayout(context, attrs) {

    var frameColor ="#999999"
    var direction ="left"
    var drawablea: Drawable? =  ContextCompat.getDrawable(context!!, R.drawable.bg_bubble_chat_left)

    init {
        initData(attrs)

        if (context != null) {
            var color = Color.parseColor(frameColor)
            //判斷左邊還右邊
            drawablea = when(direction){
                "right"->{
                    ContextCompat.getDrawable(context!!, R.drawable.bg_bubble_chat_right)
                }
                else->{
                    ContextCompat.getDrawable(context!!, R.drawable.bg_bubble_chat_left)
                }
            }
            //上色
            drawablea?.setColorFilter(color, PorterDuff.Mode.SRC_IN)
            background = drawablea
        }
    }

    private fun initData(attrs: AttributeSet?) {
        val typedArray = this.context.theme
            .obtainStyledAttributes(attrs, R.styleable.BubbleSpeech, 0, 0)
        try {
            if (typedArray.getString(R.styleable.BubbleSpeech_frameColor) != null) {
                frameColor = typedArray.getString(R.styleable.BubbleSpeech_frameColor)
            }
            if (typedArray.getString(R.styleable.BubbleSpeech_direction) != null) {
                direction = typedArray.getString(R.styleable.BubbleSpeech_direction)
            }
        } finally {
            typedArray.recycle()
        }
    }

}