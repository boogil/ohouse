package com.example.ohouse.core.di

import androidx.multidex.BuildConfig
import com.gilly.gifsearch.core.functional.Utils.createOkhttpClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
class RetrofitModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://35.200.92.60:11000/")
            .client(createOkhttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}