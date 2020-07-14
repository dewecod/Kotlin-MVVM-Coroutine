package com.dewecod.kotlin_mvvm_coroutine.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dewecod.kotlin_mvvm_coroutine.data.api.ApiHelper
import com.dewecod.kotlin_mvvm_coroutine.data.repository.MainRepository

class ViewModelFactory(private val apiHelper: ApiHelper) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(MainRepository(apiHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}