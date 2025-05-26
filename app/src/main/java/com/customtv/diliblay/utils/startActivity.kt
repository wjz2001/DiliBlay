package com.customtv.diliblay.utils

import android.content.Context
import android.content.Intent

// 简化Activity启动代码
inline fun <reified T> startActivity(context: Context, block: Intent.() -> Unit) {
    val intent = Intent(context, T::class.java)
    intent.block()
    context.startActivity(intent)
}