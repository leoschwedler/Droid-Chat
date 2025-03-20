package com.example.droidchat.features.SignInScreen.presentation.state

data class SignInUiState(
    var email: String = "",
    val isEmailError: Boolean = false,
    var password: String = "",
    val isPasswordError: Boolean = false,
    var isLoading: Boolean = false,
)
