package com.example.droidchat.features.signup.presentation.event

sealed class SignUpUiEvent {
    data class showSnackBar(val message: String) : SignUpUiEvent()
    object navigate: SignUpUiEvent()
}