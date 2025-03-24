package com.example.droidchat.features.signup.presentation.event

sealed class SignUpEvent {
    data class showSnackBar(val message: String) : SignUpEvent()
    object navigate: SignUpEvent()
}