package com.example.droidchat.commom.data.repository

import com.example.droidchat.commom.data.network.model.ImageResponse
import com.example.droidchat.commom.domain.model.CreateAccount
import com.example.droidchat.commom.domain.model.ImageDomain
import com.example.droidchat.commom.domain.model.LoginAccount

interface AuthRepository {

    suspend fun signUp(createAccount: CreateAccount): Result<Unit>

    suspend fun signIn(loginAccount: LoginAccount): Result<Unit>

    suspend fun profilePicture(filePath: String): Result<ImageDomain>

    suspend fun authenticate(token: String): Result<Unit>

    suspend fun getAccessToken(): String?

    suspend fun clearAccessToken()

}