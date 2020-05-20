package com.example.recyclerview_demo.views

import android.content.Context
import android.graphics.*
import android.graphics.drawable.NinePatchDrawable
import android.util.AttributeSet
import com.example.recyclerview_demo.R


class BubbleImageView(context: Context?, attrs: AttributeSet?) : android.support.v7.widget.AppCompatImageView(context, attrs) {

    var mImageSource = 0
    var mMaskSource = 0

    init {
        var ninePatchDrawable = context!!.resources.getDrawable(R.drawable.bg_chat) as NinePatchDrawable
        val original = BitmapFactory.decodeResource(resources, R.drawable.number_1)
        val result = Bitmap.createBitmap(original.width, original.height, Bitmap.Config.ARGB_8888)
        var mCanvas= Canvas(result)
        mCanvas.drawBitmap(original, 0f, 0f, null)

        if (ninePatchDrawable != null) {
            ninePatchDrawable.paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_OVER)
            ninePatchDrawable.bounds = Rect(0, 0,  500,  160)
            ninePatchDrawable.draw(mCanvas)
        }
        setImageBitmap(result)
        scaleType = ScaleType.CENTER
    }

//    init {
//        //获取遮罩层图片 (形狀要用他的)
//        var ninePatchDrawable = context!!.resources.getDrawable(R.drawable.bg_chat) as NinePatchDrawable
//        //获取图片的资源文件
//        val original = BitmapFactory.decodeResource(resources, R.drawable.bg_bubble_chat_left)
//        //這是畫布
//        val result = Bitmap.createBitmap(original.width, original.height, Bitmap.Config.ARGB_8888)
//        //将遮罩层的图片放到画布中
//        var mCanvas = Canvas(result)
//        mCanvas.drawBitmap(original, 0f, 0f, null)
//
//        //如果使用.9PNG就要用這種寫法
//        if (ninePatchDrawable != null) {
//            ninePatchDrawable.paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_IN)//遮蓋模式
//            ninePatchDrawable.bounds = Rect(0, 0, original.width, original.height)
//            ninePatchDrawable.draw(mCanvas)
//        }
//        //如果是用一般PNG圖
//        //畫筆
////        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
////        //设置两张图片相交时的模式
////        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.XOR)
////        mCanvas.drawBitmap(original, 0f, 0f, null)
////        //mCanvas.drawBitmap(mask, 0f, 0f, paint)
////        paint.xfermode = null
//
//        //以Bitmap當作背景
//        var bgBitmap = BitmapDrawable(result)
//        setBackgroundDrawable(bgBitmap)
//    }
}