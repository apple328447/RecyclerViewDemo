package com.example.recyclerview_demo.utils

import android.content.Context
import android.graphics.*

import android.graphics.drawable.BitmapDrawable

import android.graphics.drawable.Drawable
import android.os.Build
import android.support.v4.content.ContextCompat
import android.util.Log
import com.example.recyclerview_demo.R


object ImageUtil {


    /**
     *  Drawable 轉成 Bitmap
     *
     *  BitmapFactory.decodeResource(resources,"這裡如果放的資源檔案是drawable會回傳null,所以要先轉成bitmap,如果放的是png檔案就不有這個問題")
     *
     *  https://stackoverflow.com/questions/24389043/bitmapfactory-decoderesource-returns-null-for-shape-defined-in-xml-drawable
     * */
    fun drawableToBitmap(drawable: Drawable): Bitmap? {
        if (drawable is BitmapDrawable) {
            return drawable.bitmap
        }
        val bitmap = Bitmap.createBitmap(
            drawable.intrinsicWidth,
            drawable.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)
        return bitmap
    }

    /**Bitmap缩小的方法*/

    fun BitmapToSmall(bitmap:Bitmap ): Bitmap? {
        var matrix = Matrix()
        matrix.postScale(0.95f,0.95f)//长和宽放大缩小的比例
        return Bitmap.createBitmap(bitmap,0,0,bitmap.width,bitmap.height,matrix,true)
    }

    /**
     * 繪製氣泡恇
     *
     * **/

    fun getBubbleSpeech(context: Context): BitmapDrawable {

        //邊框(下層) drawable 染色
        var drawable = ContextCompat.getDrawable(context, R.drawable.bg_bubble_speech_all_radius_frame_blue)
        var color = Color.parseColor("#5FB100")//綠色
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            drawable?.setTint(color)
        }
        //內層圖(上層) drawable 染色
        var drawable2 = ContextCompat.getDrawable(context, R.drawable.bg_bubble_speech_all_radius_frame_blue)
        color = Color.parseColor("#FF4A43")//紅色
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            drawable2?.setTint(color)
        }
        //疊圖層
        //获取遮罩层图片 (形狀要用他的)
        var frameBitmap = ImageUtil.drawableToBitmap(drawable!!)
        //上圖層
        var original=ImageUtil.drawableToBitmap(drawable2!!)
        original=ImageUtil.BitmapToSmall(original!!)//縮小
        //這是畫布
        val result = Bitmap.createBitmap(frameBitmap!!.width, frameBitmap.height, Bitmap.Config.ARGB_8888)
        //将遮罩层的图片放到画布中
        var mCanvas = Canvas(frameBitmap)
        //畫筆
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        //设置两张图片相交时的模式
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_OVER)
//        mCanvas.drawBitmap(original, 0.1f, 0.1f, null)//沒畫筆

//        mCanvas.drawBitmap(original, 0.1f, 0.1f, paint)//有畫筆

        Log.v("Bill==下層frameBitmap==>","W:${frameBitmap.width},H:${frameBitmap.height}")
        Log.v("Bill==上層original==>","W:${original!!.width},H:${original!!.height}")


        var a =((frameBitmap.width.toFloat()-original!!.width.toFloat())/2)
        var b =((frameBitmap.height.toFloat()-original!!.height.toFloat())/2)
        Log.v("Bill==X,Y==>","X:${a},Y:${b}")
        mCanvas.drawBitmap(original, a,  b, paint)//有畫筆 置中

        paint.xfermode = null


        //以Bitmap當作背景
        return BitmapDrawable(frameBitmap)
//        setBackgroundDrawable(mBgBitmap)

    }
}