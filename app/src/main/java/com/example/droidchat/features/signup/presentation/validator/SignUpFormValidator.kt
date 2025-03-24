package com.example.droidchat.features.signup.presentation.validator

import com.example.droidchat.features.signup.presentation.state.SignUpState
import javax.inject.Inject

class SignUpFormValidator @Inject constructor(): FormValidator<SignUpState> {

    override fun validate(state: SignUpState): SignUpState {
        val isFirstNameValid = state.firstName.isNotBlank()
        val isLastNameValid = state.lastName.isNotBlank()
        val isEmailValid = EmailValidator.isValid(state.email)
        val isPasswordValid = PasswordValidator.isValid(state.password)
        val isPasswordConfirmationValid =
            PasswordValidator.isValid(state.password) && state.password == state.confirmPassword

        val hasError = listOf(
            isFirstNameValid,
            isLastNameValid,
            isEmailValid,
            isPasswordValid,
            isPasswordConfirmationValid
        ).any { !it }

        return state.copy(
            firstNameError = if (!isFirstNameValid) "Nome é obrigatório" else null,
            lastNameError = if (!isLastNameValid) "Sobrenome é obrigatório" else null,
            emailError = if (!isEmailValid) "E-mail inválido" else null,
            passwordError = if (!isPasswordValid) "Senha deve conter pelo menos uma letra e número" else null,
            confirmPasswordError = if (!isPasswordConfirmationValid) "As senhas não coincidem" else null,
            hasError = hasError
        )
    }
}