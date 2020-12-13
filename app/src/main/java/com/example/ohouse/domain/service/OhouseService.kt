package com.example.ohouse.domain.service

import com.example.ohouse.data.entity.HomeEntity
import com.example.ohouse.data.entity.SignInEntity
import com.example.ohouse.domain.api.OhouseApi
import com.google.gson.JsonObject
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import javax.inject.Inject

class OhouseService
@Inject constructor(private val retrofit: Retrofit) : OhouseApi {
    private val ohouseApi = retrofit.create(OhouseApi::class.java)

    override fun signUp(jsonObject: JsonObject): Call<SignInEntity> = ohouseApi.signUp(jsonObject)

    override fun signIn(jsonObject: JsonObject): Call<SignInEntity> = ohouseApi.signIn(jsonObject)

    override fun getHome(): Call<HomeEntity> = ohouseApi.getHome()

    override fun getPhotoFeed(page: String, per: String): Call<ResponseBody> = ohouseApi.getPhotoFeed(page, per)

    override fun getPhotoDetail(id: String): Call<ResponseBody> = ohouseApi.getPhotoDetail(id)

    override fun getUserDetail(id: String): Call<ResponseBody> = ohouseApi.getUserDetail(id)
}
