package com.example.droidchat.commom.manager

import kotlinx.coroutines.flow.Flow


interface TokenManager {
    val accessToken: Flow<String>
    suspend fun saveAccessToken(token: String)
    suspend fun clearAccessToken()
}