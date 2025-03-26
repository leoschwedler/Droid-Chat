package com.example.droidchat.commom.domain.model

import com.example.droidchat.commom.data.network.model.SignUpRequest

data class CreateAccount(
    val firstName: String,
    val lastName: String,
    val password: String,
    val profilePictureId: String?,
    val username: String
)

fun CreateAccount.toSignUpRequest(): SignUpRequest {
    return SignUpRequest(
        firstName = firstName,
        lastName = lastName,
        password = password,
        profilePictureId = profilePictureId,
        username = username
    )
}
