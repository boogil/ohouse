package com.example.ohouse.domain.api

import com.example.ohouse.data.domain.*
import com.google.gson.JsonObject
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface OhouseApi {

    /**
     * 가입
     */
    @POST("users")
    fun signUp(@Body jsonObject: JsonObject): Call<SignInEntity>

    /**
     * 로그인
     */
    @POST("users/sign_in")
    fun signIn(@Body jsonObject: JsonObject): Call<SignInEntity>

    /**
     * 홈 데이터 가져오기
     */
    @GET("home")
    fun getHome(): Call<HomeEntity>

    /**
     * 사진피드 데이터 가져오기
     */
    @GET("cards")
    fun getPhotoFeed(
        @Query("page") page: String,
        @Query("per") per: String
    ): Call<PhotoFeedEntity>

    /**
     * 사진상세 데이터 가져오기
     */
    @GET("cards/{id}")
    fun getPhotoDetail(
        @Path("id") id: String
    ): Call<PhotoDetailEntity>

    /**
     * 유저상세 데이터 가져오기
     */
    @GET("users/{id}")
    fun getUserDetail(
        @Path("id") id: String
    ): Call<UserDetailEntity>

}