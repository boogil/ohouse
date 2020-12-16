package com.example.ohouse.core.functional

import android.content.ContentValues
import android.content.Context
import android.content.Context.INPUT_METHOD_SERVICE
import android.graphics.Bitmap
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.view.Gravity
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import com.example.ohouse.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber
import java.io.*
import java.lang.Exception

object Utils {
    fun createOkhttpClient(): OkHttpClient {
        val okHttpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            val loggingInterceptor =
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)
            okHttpClientBuilder.addInterceptor(loggingInterceptor)
        }
        return okHttpClientBuilder.build()
    }

    fun toast(context: Context, str: String?) {
        str?.let {
            Toast.makeText(context, it, Toast.LENGTH_LONG).apply {
                setGravity(Gravity.CENTER, 0, 0)
            }.show()
        }
    }

}