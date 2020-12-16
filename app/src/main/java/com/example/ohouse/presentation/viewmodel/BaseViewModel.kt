package com.example.ohouse.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ohouse.core.exception.Failure

open class BaseViewModel : ViewModel() {
    private val _failure: MutableLiveData<Failure> = MutableLiveData()
    val failure: LiveData<Failure> = _failure

    protected fun handleFailure(failure: Failure?) {
        _failure.value = failure
    }
}