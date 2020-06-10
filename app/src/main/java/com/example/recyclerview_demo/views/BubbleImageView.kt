package com.example.recyclerview_demo.views

import android.content.Context
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.NinePatchDrawable
import android.os.Build
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.util.Log
import com.example.recyclerview_demo.R
import com.example.recyclerview_demo.utils.ImageUtil


class BubbleImageView(context: Context, attrs: AttributeSet?) :
    android.support.v7.widget.AppCompatImageView(context, attrs) {

    var mImageSource = 0
    var mMaskSource = 0


    init {//2020/06/09測試

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
        var mBgBitmap = BitmapDrawable(frameBitmap)
        setBackgroundDrawable(mBgBitmap)



//        //获取图片的资源文件
//        val original = BitmapFactory.decodeResource(resources, R.drawable.bg_bubble_chat_left)

//        //将遮罩层的图片放到画布中
//        var mCanvas = Canvas(result)
//        mCanvas.drawBitmap(original, 0f, 0f, null)
//        //畫筆
////        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
////        //设置两张图片相交时的模式
////        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.XOR)
////        mCanvas.drawBitmap(original, 0f, 0f, null)
////        //mCanvas.drawBitmap(mask, 0f, 0f, paint)
////        paint.xfermode = null


    }


//    init {
//        try {
//            //畫筆
//            val paint = Paint(Paint.ANTI_ALIAS_FLAG)
//
//            //這是邊框(最下層)
//            var frameMap = BitmapFactory.decodeResource(resources, R.drawable.bg_radius_all_blue)
//            //顏色
////        var color = Color.parseColor("#FFB5145D")
////        //開始染色
////        var filter: ColorFilter = PorterDuffColorFilter(color, PorterDuff.Mode.SRC_IN)
//////        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.DARKEN)
////        paint.colorFilter = filter
////        //這是畫布
////        val result1 =
////            Bitmap.createBitmap(frameMap.width, frameMap.height, Bitmap.Config.ARGB_8888)
////        //将遮罩层的图片放到画布中
////        var mCanvas1 = Canvas(result1)
////        mCanvas1.drawBitmap(frameMap, 0f, 0f, paint)//染色
//////        frameMap = BitmapDrawable(result1).bitmap
//
//
//            //以Bitmap當作背景
//            var bgBitmap = BitmapDrawable(frameMap)
//            setBackgroundDrawable(bgBitmap)
//////============上層底色染色=====================================
////        var baseMap = BitmapFactory.decodeResource(resources, R.drawable.bg_chatqwe)
////        //顏色
////        color = Color.parseColor("#FFF2CE")
////        //開始染色
////        filter = PorterDuffColorFilter(color, PorterDuff.Mode.SRC_IN)
////        paint.colorFilter = filter
////
////        //這是畫布
////        val result2 =
////            Bitmap.createBitmap(baseMap.width, baseMap.height, Bitmap.Config.ARGB_8888)
////        //将遮罩层的图片放到画布中
////        var mCanvas2 = Canvas(result2)
////        mCanvas2.drawBitmap(result2, 0f, 0f, paint)//染色
////        baseMap = BitmapDrawable(result2).bitmap
//////=============開始疊圖層==============================================
////        //這是畫布
////        var result =
////            Bitmap.createBitmap(frameMap.width, frameMap.height, Bitmap.Config.ARGB_8888)
////        var mCanvas = Canvas(result)
////        var paint2 = Paint(Paint.ANTI_ALIAS_FLAG)
////        paint2.xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_OVER)
////        mCanvas.drawBitmap(baseMap, 0f, 0f, paint)//疊圖層
//
//
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//
//
////            //這是畫布
////        val result = Bitmap.createBitmap(frameMap.width, frameMap.height, Bitmap.Config.ARGB_8888)
////        //将遮罩层的图片放到画布中
////        var mCanvas = Canvas(result)
////        mCanvas.drawBitmap(frameMap, 0f, 0f, null)
//        //mCanvas.drawBitmap(mask, 0f, 0f, paint)
//
//
//        //如果是用一般PNG圖
//
//        //畫筆
////        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
//        //设置两张图片相交时的模式
////        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.XOR)
////            var color = Color.parseColor("#FFB5145D")
////            val filter: ColorFilter = PorterDuffColorFilter(color, PorterDuff.Mode.SRC_IN)
//
//
//        //以Bitmap當作背景
////        var bgBitmap = BitmapDrawable(result)
////        setBackgroundDrawable(bgBitmap)
//    }

//    init {
//        var ninePatchDrawable = context!!.resources.getDrawable(R.drawable.bg_chat) as NinePatchDrawable
//        val original = BitmapFactory.decodeResource(resources, R.drawable.number_1)
//        val result = Bitmap.createBitmap(original.width, original.height, Bitmap.Config.ARGB_8888)
//
//        var mCanvas= Canvas(result)
//        mCanvas.drawBitmap(original, 0f, 0f, null)
//
//        if (ninePatchDrawable != null) {
//            ninePatchDrawable.paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_OVER)
//            ninePatchDrawable.bounds = Rect(0, 0,  500,  160)
//            ninePatchDrawable.draw(mCanvas)
//        }
//
//        setImageBitmap(result)
//        scaleType = ScaleType.CENTER
//    }

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
    //Drawable
//    var drawable2 = ContextCompat.getDrawable(this.activity, R.drawable.bg_bubble_speech_all_radius_frame_blue)
//    var color2 = Color.parseColor(data.role?.bgColor?.substring(0, 7))
//    drawable2?.setTint(color2) 或是 drawable?.setColorFilter(color, PorterDuff.Mode.DST_IN)//濾鏡效果
}