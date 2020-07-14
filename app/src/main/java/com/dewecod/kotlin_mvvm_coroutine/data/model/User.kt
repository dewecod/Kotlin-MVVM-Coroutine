package com.dewecod.kotlin_mvvm_coroutine.data.model

import com.google.gson.annotations.SerializedName

data class User(
        @SerializedName("avatar") val avatar: String,
        @SerializedName("email") val email: String,
        @SerializedName("id") val id: String,
        @SerializedName("name") val name: String
)