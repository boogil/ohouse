package com.gilly.gifsearch.core.functional

import android.content.ContentValues
import android.content.Context
import android.content.Context.INPUT_METHOD_SERVICE
import android.graphics.Bitmap
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
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

    fun setKeyboardVisibility(context: Context, editText: EditText, visible: Boolean) {
        val imm = context.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        if (visible) {
            imm.showSoftInput(editText, 0)
        } else {
            imm.hideSoftInputFromWindow(editText.windowToken, 0)
        }

    }


    /**
     * 앨범에 이미지 저정하기
     * (앨범 경로: sdcard/DCIM)
     */
    fun saveImageInAlbum(context: Context, folderName: String, fileName: String, bitmap: Bitmap) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val values = ContentValues()
            with(values) {
                put(MediaStore.Images.Media.TITLE, fileName)
                put(MediaStore.Images.Media.DATE_TAKEN, System.currentTimeMillis())
                put(MediaStore.Images.Media.RELATIVE_PATH, "DCIM/$folderName")
                put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
            }

            val uri = context.getContentResolver()
                .insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
            val fos = context.contentResolver.openOutputStream(uri!!)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos)
            fos?.run {
                flush()
                close()
            }
        } else {
            val dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)
                .toString() +
                    File.separator +
                    "$folderName"
            val file = File(dir)
            if (!file.exists()) {
                file.mkdirs()
            }

            val imgFile = File(file, fileName)
            val os = FileOutputStream(imgFile)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, os)
            os.flush()
            os.close()

            val values = ContentValues()
            with(values) {
                put(MediaStore.Images.Media.TITLE, fileName)
                put(MediaStore.Images.Media.DATE_TAKEN, System.currentTimeMillis())
                put(MediaStore.Images.Media.BUCKET_ID, fileName)
                put(MediaStore.Images.Media.DATA, imgFile.absolutePath)
                put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
            }

            context.contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
        }
    }

    /**
     * 앨범에 이미지 저정하기
     * (앨범 경로: sdcard/DCIM)
     */
    fun saveImageInAlbum(
        context: Context,
        folderName: String,
        fileName: String,
        byteArray: ByteArray,
    ): Boolean {

        return try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                val values = ContentValues()
                with(values) {
                    put(MediaStore.Images.Media.TITLE, fileName)
                    put(MediaStore.Images.Media.DATE_TAKEN, System.currentTimeMillis())
                    put(MediaStore.Images.Media.RELATIVE_PATH, "DCIM/$folderName")
                    put(MediaStore.Images.Media.MIME_TYPE, "image/gif")
                }

                val uri = context.getContentResolver()
                    .insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
                val fos = context.contentResolver.openOutputStream(uri!!)

                fos?.run {
                    write(byteArray, 0, byteArray.size)
                    flush()
                    close()
                }
            } else {
                val dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)
                    .toString() +
                        File.separator +
                        "$folderName"
                val file = File(dir)
                if (!file.exists()) {
                    file.mkdirs()
                }

                val imgFile = File(file, fileName)
                val fos = FileOutputStream(imgFile)
                fos.write(byteArray, 0, byteArray.size)
                fos.flush()
                fos.close()

                val values = ContentValues()
                with(values) {
                    put(MediaStore.Images.Media.TITLE, fileName)
                    put(MediaStore.Images.Media.DATE_TAKEN, System.currentTimeMillis())
                    put(MediaStore.Images.Media.BUCKET_ID, fileName)
                    put(MediaStore.Images.Media.DATA, imgFile.absolutePath)
                    put(MediaStore.Images.Media.MIME_TYPE, "image/gif")
                }

                context.contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
            }

            true
        } catch (e: Exception) {
            Timber.v("saveImageInAlbum exception: $e")

            false
        }
    }

    /**
     * 앨범에 이미지 저정하기
     * (앨범 경로: sdcard/DCIM)
     */
    fun saveImageInPrivate(
        context: Context,
        folderName: String,
        fileName: String,
        byteArray: ByteArray,
    ): File? {

        return try {
            val file = File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), folderName)
            if (!file.exists()) {
                file.mkdirs()
            }

            val imgFile = File(file, fileName)
            val fos = FileOutputStream(imgFile)
            fos.write(byteArray, 0, byteArray.size)
            fos.flush()
            fos.close()

            return imgFile
        } catch (e: Exception) {
            Timber.v("saveImageInAlbum exception: $e")

            null
        }
    }

    /**
     * 앨범에 이미지 저정하기
     * (앨범 경로: sdcard/DCIM)
     */
    fun loadImageInPrivate(context: Context, folderName: String, fileName: String): File {
        return File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), folderName)
    }

    fun convertFileToByteArray(file: File): ByteArray {
        val fis = FileInputStream(file)
        val bis = BufferedInputStream(fis)
        val baos = ByteArrayOutputStream()

        var current = 0
        while (bis.read().also { current = it } != -1) {
            baos.write(current)
        }
        return baos.toByteArray()
    }

}