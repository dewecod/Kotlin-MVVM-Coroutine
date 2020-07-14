package com.dewecod.kotlin_mvvm_coroutine.data.api

class ApiHelper(private val apiService: ApiService) {
    suspend fun getUserList() = apiService.getUserList()
}