package com.example.droidchat.commom.data.repository

import com.example.droidchat.commom.domain.model.CreateAccount
import com.example.droidchat.commom.domain.model.LoginAccount

interface AuthRepository {

    suspend fun signUp(createAccount: CreateAccount)

    suspend fun signIn(loginAccount: LoginAccount)

}