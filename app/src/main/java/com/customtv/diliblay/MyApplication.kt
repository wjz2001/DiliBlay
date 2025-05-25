package com.customtv.diliblay

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class MyApplication : Application() {
    companion object {
        // 此处Context全局只会存在一份实例，不会内存泄漏，添加注解使Android Studio忽略内存泄漏警告
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
            private set
    }
    override fun onCreate() {
        super.onCreate()
        // 以静态变量的形式获取Context对象
        context = applicationContext
    }
}