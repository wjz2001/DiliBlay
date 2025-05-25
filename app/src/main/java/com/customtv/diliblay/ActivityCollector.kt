package com.customtv.diliblay

import android.app.Activity

object ActivityCollector {
    // 通过一个ArrayList来暂存Activity
    private val activities = ArrayList<Activity>()

    // 向ArrayList中添加Activity
    fun addActivity(activity: Activity) {
        activities.add(activity)
    }

    // 从ArrayList中移除Activity
    fun removeActivity(activity: Activity) {
        activities.remove(activity)
    }

    // 遍历ArrayList并调用其finish()方法来结束所有Activity
    fun finishAll() {
        // 判断Activity是否正在销毁中, 如果正在销毁中则跳过
        for (activity in activities) {
            if (!activity.isFinishing) {
                activity.finish()
            }
        }
        activities.clear()
    }
}