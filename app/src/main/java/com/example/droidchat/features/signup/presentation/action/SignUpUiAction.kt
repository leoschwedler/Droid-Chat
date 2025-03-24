package com.example.droidchat.features.signup.presentation.action

import android.net.Uri

sealed class SignUpUiAction {
    data class onNameChange(val name: String) : SignUpUiAction()
    data class onLastNameChange(val lastName: String) : SignUpUiAction()
    data class onEmailChange(val email: String) : SignUpUiAction()
    data class onPasswordChange(val password: String) : SignUpUiAction()
    data class onConfirmPasswordChange(val confirmPassword: String) : SignUpUiAction()
    data class onBottomSheetChange(val bottomSheet: Boolean) : SignUpUiAction()
    data class onProfilePictureSelected(val uri: Uri) : SignUpUiAction()
    object onExtraTextChange : SignUpUiAction()
    object onSubmit : SignUpUiAction()
}