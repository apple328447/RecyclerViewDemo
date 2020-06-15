package com.example.recyclerview_demo.utils

import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.graphics.drawable.InsetDrawable //內縮用
import android.graphics.drawable.LayerDrawable //有使用<layer-list>用
import android.graphics.drawable.VectorDrawable //有使用向量圖用
import android.os.Build
import android.support.v4.content.ContextCompat
import com.example.recyclerview_demo.R
import java.lang.Exception

object BubbleSpeechUtil {

    fun getBubbleSpeech(context: Context, colorTop: String, colorBottom: String,listener:OnSetColorListener): Drawable? {
        val drawable =
                ContextCompat.getDrawable(context, R.drawable.bg_bubble_speech)
        try {
            val color = Color.parseColor(colorBottom)
            val color2 = Color.parseColor(colorTop)

            (((drawable as LayerDrawable).getDrawable(0)as LayerDrawable).getDrawable(0) as VectorDrawable).mutate().setColorFilter(color, PorterDuff.Mode.SRC_IN)
            (drawable.getDrawable(0)as LayerDrawable).getDrawable(1).mutate().setColorFilter(color, PorterDuff.Mode.SRC_IN)

            ((drawable.getDrawable(1)as LayerDrawable).getDrawable(0) as VectorDrawable).mutate().setColorFilter(color2, PorterDuff.Mode.SRC_IN)
            (drawable.getDrawable(1)as LayerDrawable).getDrawable(1).mutate().setColorFilter(color2, PorterDuff.Mode.SRC_IN)

            listener.onReceive(drawable)

        } catch (e: Exception) {
            e.printStackTrace()
        }
        return drawable
    }

    interface OnSetColorListener {
        fun onReceive(bubbleSpeech:Drawable)
        fun onError()
    }

}