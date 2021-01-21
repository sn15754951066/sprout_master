package com.example.myapplication.data

data class LoginData(
    val code: Int,
    val token: String,
    val userInfo: UserInfo
)

data class UserInfo(
    val avatar: String,
    val birthday: Int,
    val gender: Int,
    val nickname: Any,
    val uid: String,
    val username: String
)