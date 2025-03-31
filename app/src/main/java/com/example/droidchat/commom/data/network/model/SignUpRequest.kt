package com.example.droidchat.commom.data.network.model

import kotlinx.serialization.Serializable

@Serializable
data class SignUpRequest(
    val firstName: String,
    val lastName: String,
    val password: String,
    val profilePictureId: Int?,
    val username: String
)
