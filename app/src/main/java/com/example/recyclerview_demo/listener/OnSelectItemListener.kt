package com.example.recyclerview_demo.listener

interface OnSelectItemListener<T> {
    fun onClick(select: T)
}