package com.example.droidchat.features.signup.presentation.state

import android.net.Uri

data class SignUpUiState(
    val bottomSheet: Boolean = false,
    val profilePictureUri: Uri? = null,
    val name: String = "",
    val nameError: String? = null,
    val lastName: String = "",
    val lastNameError: String? = null,
    val email: String = "",
    val emailError: String? = null,
    val password: String = "",
    val passwordError: String? = null,
    val confirmPassword: String = "",
    val confirmPasswordError: String? = null,
    val loading: Boolean = false
)
