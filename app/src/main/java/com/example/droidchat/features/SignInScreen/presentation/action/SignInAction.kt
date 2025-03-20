package com.example.droidchat.features.SignInScreen.presentation.action

sealed class SignInAction {

    data class onEmailChange(val email: String) : SignInAction()
    data class onPasswordChange(val password: String) : SignInAction()
    data object onSubmit : SignInAction()
}