package com.example.ohouse.domain.repository.network

import com.example.ohouse.core.exception.Failure
import com.example.ohouse.data.Home
import com.example.ohouse.domain.service.OhouseService
import com.gilly.gifsearch.core.functional.Either
import com.gilly.gifsearch.core.functional.NetworkHandler
import com.google.gson.JsonObject
import javax.inject.Inject

class OhouseRepositoryNetwork @Inject constructor(
    private val networkHandler: NetworkHandler,
    private val ohouseService: OhouseService
) : NetworkTypeCall() {

    /**
     * 회원가입
     */
    fun signUp(nickName: String, introduction: String, pwd: String): Either<Failure, String> {
        return when (networkHandler.isConnected()) {
            true -> {
                request(ohouseService.signUp(JsonObject().apply {
                    addProperty("nickname", nickName)
                    addProperty("introduction", introduction)
                    addProperty("pwd", pwd)
                }), { signInEntity ->
                    signInEntity.toString()
                }, "")
            }
            false -> Either.Left(Failure.NetworkConnection)
        }
    }

    /**
     * 로그인
     */
    fun signIn(nickName: String, pwd: String): Either<Failure, String> {
        return when (networkHandler.isConnected()) {
            true -> {
                request(ohouseService.signUp(JsonObject().apply {
                    addProperty("nickname", nickName)
                    addProperty("pwd", pwd)
                }), { signInEntity ->
                    signInEntity.toId()
                }, "")
            }
            false -> Either.Left(Failure.NetworkConnection)
        }
    }

    /**
     * 홈 데이터 가져오기
     */
    fun getHome(): Either<Failure, Home> {
        return when (networkHandler.isConnected()) {
            true -> {
                request(ohouseService.getHome(), { homeEntity ->
                    homeEntity.toHome()
                }, Home.empty())
            }
            false -> Either.Left(Failure.NetworkConnection)
        }
    }


    //TODO 사진피드 데이터 가져오기 부터 작업하기
//    fun getPhotoFeed(page: Int, per: Int)

}