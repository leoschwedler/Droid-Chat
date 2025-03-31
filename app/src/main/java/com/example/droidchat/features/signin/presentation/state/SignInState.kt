package com.example.droidchat.features.signin.presentation.state

data class SignInState(
    var email: String = "",
    val emailErrorMessage: String? = null,
    var password: String = "",
    val passwordErrorMessage: String? = null,
    var isLoading: Boolean = false,
    var hasError: Boolean = false,
    var isSignedIn: Boolean = false,
    val apiErrorMessageResId: String? = null,
)
