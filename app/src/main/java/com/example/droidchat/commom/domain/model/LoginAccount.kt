package com.example.droidchat.commom.domain.model

import com.example.droidchat.commom.data.network.model.SignInRequest

data class LoginAccount(
    val username: String,
    val password: String
)

fun LoginAccount.toSignInRequest() = SignInRequest(username = username, password = password)
