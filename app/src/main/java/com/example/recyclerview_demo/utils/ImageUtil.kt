package com.example.recyclerview_demo.utils

import android.content.Context
import android.graphics.*

import android.graphics.drawable.BitmapDrawable

import android.graphics.drawable.Drawable



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
}