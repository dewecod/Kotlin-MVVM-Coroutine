package com.dewecod.kotlin_mvvm_coroutine.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dewecod.kotlin_mvvm_coroutine.data.api.ApiService
import com.dewecod.kotlin_mvvm_coroutine.data.repository.MainRepository

class ViewModelFactory(private val apiService: ApiService) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(MainRepository(apiService)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}