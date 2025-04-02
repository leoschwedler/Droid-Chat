package com.example.droidchat.commom.data.network.model

import kotlinx.serialization.Serializable

@Serializable
data class AuthenticateResponse(
    val firstName: String,
    val lastName: String,
    val id: Int,
    val profilePictureUrl: String?,
    val username: String
)
