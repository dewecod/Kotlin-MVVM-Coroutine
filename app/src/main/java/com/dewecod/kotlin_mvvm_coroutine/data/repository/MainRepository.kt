package com.dewecod.kotlin_mvvm_coroutine.data.repository

import com.dewecod.kotlin_mvvm_coroutine.data.api.ApiHelper

class MainRepository(private val apiHelper: ApiHelper) {
    suspend fun getUserList() = apiHelper.getUserList()
}