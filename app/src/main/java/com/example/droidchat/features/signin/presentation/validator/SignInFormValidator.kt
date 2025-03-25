package com.example.droidchat.features.signin.presentation.validator

import com.example.droidchat.commom.validator.EmailValidator
import com.example.droidchat.commom.validator.FormValidator
import com.example.droidchat.commom.validator.PasswordValidator
import com.example.droidchat.features.signin.presentation.state.SignInState
import javax.inject.Inject

class SignInFormValidator @Inject constructor(): FormValidator<SignInState> {
    override fun validate(state: SignInState): SignInState {
        val isEmailValid = EmailValidator.isValid(state.email)
        val isPasswordValid = PasswordValidator.isValid(state.password)


        val hasError = listOf(
            isEmailValid,
            isPasswordValid,

            ).any { !it }

        return state.copy(
            emailErrorMessage = if (!isEmailValid) "E-mail inválido" else null,
            passwordErrorMessage = if (!isPasswordValid) "Senha inválida" else null,
            hasError = hasError
        )
    }
}