package com.example.droidchat.commom.data.datasource.networkdatasouce

import com.example.droidchat.commom.data.network.model.AuthenticateResponse
import com.example.droidchat.commom.data.network.model.ImageResponse
import com.example.droidchat.commom.data.network.model.SignInRequest
import com.example.droidchat.commom.data.network.model.SignUpRequest
import com.example.droidchat.commom.data.network.model.TokenResponse

interface NetworkDataSource {

    suspend fun signUp(signUpRequest: SignUpRequest)

    suspend fun signIn(signInRequest: SignInRequest): TokenResponse

    suspend fun profilePicture(filePath: String): ImageResponse

    suspend fun authenticate(token: String): AuthenticateResponse

}