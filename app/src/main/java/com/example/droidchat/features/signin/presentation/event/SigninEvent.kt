package com.example.droidchat.features.signin.presentation.event

sealed class SigninEvent{
    data object IsSucess: SigninEvent()
    sealed class Error: SigninEvent() {
        data object GenericError: Error()
        data object UnauthorizedError: Error()
    }
}
