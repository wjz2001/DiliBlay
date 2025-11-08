
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

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
    alias(libs.plugins.google.dagger.hilt)
    alias(libs.plugins.google.devtools.ksp)
    id("com.google.protobuf") version "0.9.5"
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
        dataBinding = true
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
    implementation(libs.androidx.media3.common.ktx)
    implementation(libs.androidx.activity)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation("com.guolindev.permissionx:permissionx:1.8.1")
    implementation("com.squareup.retrofit2:retrofit:3.0.0")
    implementation("com.squareup.retrofit2:converter-gson:3.0.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.2")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.10.2")
    implementation("androidx.constraintlayout:constraintlayout:2.2.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.9.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.9.4")
    implementation("androidx.activity:activity-ktx:1.11.0")
    implementation("androidx.fragment:fragment-ktx:1.8.9")
    implementation("androidx.hilt:hilt-lifecycle-viewmodel:1.3.0")
    ksp("androidx.hilt:hilt-compiler:1.3.0")
    implementation("com.google.dagger:hilt-android:2.57.2")
    ksp("com.google.dagger:hilt-android-compiler:2.57.2")
    implementation("com.hankcs:hanlp:portable-1.8.6")
    implementation("androidx.datastore:datastore-preferences:1.1.7")
    implementation("com.google.protobuf:protobuf-javalite:4.33.0")
    implementation("com.github.DylanCaiCoding.DataStoreKTX:datastore-ktx:1.0.0")
    implementation("com.github.DylanCaiCoding.Longan:longan:1.1.1")
    implementation("com.github.DylanCaiCoding.Longan:longan-design:1.1.1")
    implementation("com.github.DylanCaiCoding.ViewBindingKTX:viewbinding-ktx:2.1.0")
    implementation("com.github.DylanCaiCoding.ViewBindingKTX:viewbinding-nonreflection-ktx:2.1.0")
    implementation("com.github.DylanCaiCoding.ViewBindingKTX:viewbinding-base:2.1.0")
    implementation("com.github.DylanCaiCoding.ViewBindingKTX:viewbinding-brvah:2.1.0")
}