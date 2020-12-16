package com.example.ohouse.domain.repository.network

import com.example.ohouse.core.exception.Failure
import com.example.ohouse.data.data.Card
import com.example.ohouse.data.data.Home
import com.example.ohouse.data.data.PhotoDetail
import com.example.ohouse.data.data.User
import com.example.ohouse.domain.service.OhouseService
import com.example.ohouse.core.functional.Either
import com.example.ohouse.core.functional.NetworkHandler
import com.google.gson.JsonObject
import javax.inject.Inject

/**
 * Network 통신용 Repository
 * (Local DB 활용 -> OhouseRepositoryLocal.class 사용)
 */
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
                request(ohouseService.signIn(JsonObject().apply {
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


    /**
     * 사진피드 데이터 가져오기
     * @param page 페이징
     * @param per 아이템 갯수
     */
    fun getPhotoFeed(page: Int, per: Int): Either<Failure, ArrayList<Card>> {
        return when (networkHandler.isConnected()) {
            true -> {
                request(ohouseService.getPhotoFeed(page.toString(), per.toString()), { photoFeedEntity ->
                    photoFeedEntity.toCards()
                }, arrayListOf())
            }
            false -> Either.Left(Failure.NetworkConnection)
        }
    }

    /**
     * 사진 상세 데이터 가져오기
     */
    fun getPhotoDetail(id: Int): Either<Failure, PhotoDetail> {
        return when (networkHandler.isConnected()) {
            true -> {
                request(ohouseService.getPhotoDetail(id.toString()), { photoDetailEntity ->
                    photoDetailEntity.toPhotoDetail()
                }, PhotoDetail.empty())
            }
            false -> Either.Left(Failure.NetworkConnection)
        }
    }

    /**
     * 유저 상세 데이터 가져오기
     */
    fun getUserDetail(id: Int): Either<Failure, User> {
        return when (networkHandler.isConnected()) {
            true -> {
                request(ohouseService.getUserDetail(id.toString()), { userDetailEntity ->
                    userDetailEntity.toUser()
                }, User.empty())
            }
            false -> Either.Left(Failure.NetworkConnection)
        }
    }

}