package com.putrash.kumparantask.base

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {
    private val _isLoading = MutableLiveData(View.GONE)
    val isLoading: LiveData<Int> get() = _isLoading

    protected fun showLoading() {
        _isLoading.postValue(View.VISIBLE)
    }

    protected fun hideLoading() {
        _isLoading.postValue(View.GONE)
    }

    private val _errorMessage = MutableLiveData("")
    val errorMessage: LiveData<String> get() = _errorMessage

    protected fun showError(message: String?) {
        _errorMessage.postValue(message)
    }
}