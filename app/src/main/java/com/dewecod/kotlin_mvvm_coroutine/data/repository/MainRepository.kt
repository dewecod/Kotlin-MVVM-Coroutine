package com.dewecod.kotlin_mvvm_coroutine.data.repository

import com.dewecod.kotlin_mvvm_coroutine.data.api.ApiService

class MainRepository(private val apiService: ApiService) {
    suspend fun getUserList() = apiService.getUserList()
}