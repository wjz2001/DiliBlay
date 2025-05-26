package com.customtv.diliblay.utils

import android.content.Context
import android.content.pm.PackageInfo
import com.customtv.diliblay.MyApplication

fun compareCurrentAppVersionWith(newVersionToCompare: String): Int {
    // 获取当前应用的版本号
    fun getAppVersionName(context: Context): String {
            val packageInfo: PackageInfo = context.packageManager.getPackageInfo(context.packageName, 0)
            return packageInfo.versionName
    }
    val currentAppVersion = getAppVersionName(MyApplication.context)

    // 按 "." 分割版本号
    val partsCurrent = currentAppVersion.split('.')
    val partsNew = newVersionToCompare.split('.')

    // 校验版本号段数是否为4
    if (partsCurrent.size != 4 || partsNew.size != 4) {
        val errorMsg = StringBuilder("版本号格式错误，")
        if (partsCurrent.size != 4) {errorMsg.append("当前版本 ${currentAppVersion} 不是4段。")}
        if (partsNew.size != 4) {errorMsg.append("比较版本 ${newVersionToCompare} 不是4段。")}
        throw IllegalArgumentException(errorMsg.toString())
    }

    try {
        // 将字符串段转换为整数列表
        val numPartsCurrent = partsCurrent.map { it.toInt() }
        val numPartsNew = partsNew.map { it.toInt() }

        // 逐段比较 newVersionToCompare 和 currentAppVersion
        for (i in 0 until 4) { // 0, 1, 2, 3
            if (numPartsNew[i] > numPartsCurrent[i]) {
                return 1 // newVersionToCompare 的当前段更大，所以 newVersionToCompare 更新
            }
            if (numPartsNew[i] < numPartsCurrent[i]) {
                return -1 // newVersionToCompare 的当前段更小，所以 newVersionToCompare 更旧
            }
            // 如果当前段相等，则继续比较下一段
        }

        // 如果所有段都相等，则版本号相同
        return 0

    } catch (e: NumberFormatException) {
        throw IllegalArgumentException("版本号的某一段不是有效的数字。当前: ${currentAppVersion}, 比较: ${newVersionToCompare}", e)
    }
}