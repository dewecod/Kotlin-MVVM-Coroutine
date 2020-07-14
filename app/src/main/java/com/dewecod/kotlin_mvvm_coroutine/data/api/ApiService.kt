package com.dewecod.kotlin_mvvm_coroutine.data.api

import com.dewecod.kotlin_mvvm_coroutine.data.model.User
import retrofit2.http.GET

interface ApiService {
    @GET("users")
    suspend fun getUserList(): List<User>
}