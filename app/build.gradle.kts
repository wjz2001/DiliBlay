import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale
import org.gradle.api.JavaVersion

// 获取当前年月日时分，转化为版本名
fun getVersionName(): String {
    // 获取当前日期与时间
    val currentDate = LocalDateTime.now()
    // 格式化为两位年份、两位月份、两位日子、两位小时、两位分钟，使用中国时区
    val versionDateTimeFormatter = DateTimeFormatter.ofPattern("yy.MM.dd.HHmm", Locale.CHINA)
    // 格式化后的日期输出为版本名
    return currentDate.format(versionDateTimeFormatter)
}

// 生成始终增加的版本号
fun calculateVersionCode(): Int {
    // 使用时间戳（秒）
    val timestampCode = (System.currentTimeMillis() / 1000).toInt()
    println("最新VersionCode: $timestampCode")
    return timestampCode
}

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.customtv.diliblay"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.customtv.diliblay"
        minSdk = 21
        targetSdk = 34
        versionName = getVersionName()
        versionCode = calculateVersionCode()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        viewBinding = true
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    // 自定义 APK 输出文件名
    androidComponents {
        onVariants { variant ->
            variant.outputs.forEach { output ->
                // 构建新的文件名，大写后缀名以防止QQ添加.1
                if (output is com.android.build.api.variant.impl.VariantOutputImpl) {
                    output.outputFileName.set("DiliBlay删档内测${output.versionName.get()}.APK")
                    println("已修改文件名为${output.outputFileName.get()}")
                }
            }
        }
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation("com.guolindev.permissionx:permissionx:1.8.1")
    implementation("com.squareup.retrofit2:retrofit:3.0.0")
    implementation("com.squareup.retrofit2:converter-gson:3.0.0")
}