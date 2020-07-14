package com.dewecod.kotlin_mvvm_coroutine.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.dewecod.kotlin_mvvm_coroutine.data.repository.MainRepository
import com.dewecod.kotlin_mvvm_coroutine.util.Resource
import kotlinx.coroutines.Dispatchers

class MainViewModel(private val repository: MainRepository) : ViewModel() {
    fun getUserList() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = repository.getUserList()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Something went wrong!"))
        }
    }

}