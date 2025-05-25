package com.customtv.diliblay

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat

open class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 强制应用横屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)

        // 允许内容绘制到系统栏区域
        WindowCompat.setDecorFitsSystemWindows(window, false)

        // 初始化WindowInsetsControllerCompat
        val windowInsetsController = WindowInsetsControllerCompat(window, window.decorView)

        // 滑动时临时显示系统栏
        windowInsetsController.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE

        // 隐藏状态栏、导航栏
        windowInsetsController.hide(WindowInsetsCompat.Type.statusBars())
        windowInsetsController.hide(WindowInsetsCompat.Type.navigationBars())

        // 将当前正在创建的Activity添加到集合里
        ActivityCollector.addActivity(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        // 从集合里移除一个马上要销毁的Activity
        ActivityCollector.removeActivity(this)
    }
}