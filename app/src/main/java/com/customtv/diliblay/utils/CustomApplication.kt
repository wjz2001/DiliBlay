package com.customtv.diliblay.utils

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

// Hilt依赖注入入口
@HiltAndroidApp
class CustomApplication : Application() {

    companion object {
        // 该方法不会内存泄漏
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
            private set
    }

    override fun onCreate() {
        super.onCreate()
        // 全局获取Application中的Context
        context = applicationContext
    }
}