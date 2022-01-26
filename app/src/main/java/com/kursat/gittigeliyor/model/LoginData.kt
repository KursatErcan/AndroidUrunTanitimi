package com.kursat.gittigeliyor.model

import com.google.gson.annotations.SerializedName

data class LoginData(
    @SerializedName("user")
    val userResult: List<UserLoginResult>
)
data class UserLoginResult(
    @SerializedName("bilgiler")
    val user: User?,
    @SerializedName("durum")
    val durum: Boolean,
    @SerializedName("mesaj")
    val mesaj: String
)
data class User(
    @SerializedName("userEmail")
    val userEmail: String,
    @SerializedName("userId")
    val userId: String,
    @SerializedName("userName")
    val userName: String,
    @SerializedName("userPhone")
    val userPhone: String,
    @SerializedName("userSurname")
    val userSurname: String,
)