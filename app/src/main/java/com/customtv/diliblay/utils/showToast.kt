package com.customtv.diliblay.utils

import android.widget.Toast
import com.customtv.diliblay.MyApplication

// 扩展String类，简化Toast用法
fun String.showToast(duration: String = "short") {
    val _duration = when (duration) {
        "short" -> Toast.LENGTH_SHORT
        "long" -> Toast.LENGTH_LONG
        else -> Toast.LENGTH_LONG
    }
    Toast.makeText(MyApplication.context, this, _duration).show()
}

// 扩展Int类，简化Toast用法
fun Int.showToast(duration: String = "short") {
    val _duration = when (duration) {
        "short" -> Toast.LENGTH_SHORT
        "long" -> Toast.LENGTH_LONG
        else -> Toast.LENGTH_LONG
    }
    Toast.makeText(MyApplication.context, this, _duration).show()
}