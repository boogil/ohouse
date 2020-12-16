package com.example.ohouse

import androidx.multidex.BuildConfig
import androidx.multidex.MultiDexApplication
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class AndroidApplication : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
        }
    }

    companion object {
        var LOGGED_IN = false // 로그인 여부 체크 (임시용도)
    }
}