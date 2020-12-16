package com.example.ohouse.presentation.viewmodel

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.example.ohouse.data.data.Card
import com.example.ohouse.data.data.Home
import com.example.ohouse.data.data.PhotoDetail
import com.example.ohouse.data.data.User
import com.example.ohouse.domain.usecase.*

class OhouseViewModel
@ViewModelInject constructor(
    private val getHome: GetHome,
    private val getPhotoDetail: GetPhotoDetail,
    private val getPhotoFeed: GetPhotoFeed,
    private val getUserDetail: GetUserDetail,
    private val signIn: SignIn,
    private val signUp: SignUp,
    @Assisted private val savedStateHandle: SavedStateHandle
) : BaseViewModel() {
    private val _home: MutableLiveData<Home> = MutableLiveData(Home.empty())
    private val _photoDetail: MutableLiveData<PhotoDetail> = MutableLiveData(PhotoDetail.empty())
    private val _photoFeeds: MutableLiveData<ArrayList<Card>> = MutableLiveData(arrayListOf())
    private val _userDetail: MutableLiveData<User> = MutableLiveData(User.empty())
    private val _signInResult: MutableLiveData<String> = MutableLiveData("")
    private val _signUpResult: MutableLiveData<String> = MutableLiveData("")

    val home: LiveData<Home> = _home
    val photoDetail: LiveData<PhotoDetail> = _photoDetail
    val photoFeeds: LiveData<ArrayList<Card>> = _photoFeeds
    val userDetail: LiveData<User> = _userDetail
    val signInResult: LiveData<String> = _signInResult
    val signUpResult: LiveData<String> = _signUpResult

    fun getHome() = getHome(UseCase.None()) { it.fold(::handleFailure, ::handleSuccess) }

    fun getPhotoDetail(id: Int) = getPhotoDetail(GetPhotoDetail.Params(id)) { it.fold(::handleFailure, ::handleSuccess) }

    fun getPhotoFeed(page: Int, per: Int) = getPhotoFeed(GetPhotoFeed.Params(page, per)) { it.fold(::handleFailure, ::handleSuccess) }

    fun getUserDetail(id: Int) = getUserDetail(GetUserDetail.Params(id)) { it.fold(::handleFailure, ::handleSuccess) }

    fun signIn(nickName: String, pwd: String) = signIn(SignIn.Params(nickName, pwd)) { it.fold(::handleFailure, ::handleSuccessSignIn) }

    fun signUp(nickName: String, introduction: String, pwd: String) = signUp(SignUp.Params(nickName, introduction, pwd)) { it.fold(::handleFailure, ::handleSuccessSignUp) }

    private fun handleSuccess(home: Home) {
        _home.value = home
    }

    private fun handleSuccess(photoDetail: PhotoDetail) {
        _photoDetail.value = photoDetail
    }

    private fun handleSuccess(photoFeeds: ArrayList<Card>) {
        _photoFeeds.value = photoFeeds
    }

    private fun handleSuccess(userDetail: User) {
        _userDetail.value = userDetail
    }

    private fun handleSuccessSignIn(signInId: String) {
        _signInResult.value = signInId
    }

    private fun handleSuccessSignUp(signUpId: String) {
        _signUpResult.value = signUpId
    }
}