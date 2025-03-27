package com.example.droidchat.features.signup.presentation.state

import android.net.Uri

data class SignUpState(
    val bottomSheet: Boolean = false,
    val profilePictureUri: Uri? = null,
    val firstName: String = "",
    val firstNameError: String? = null,
    val lastName: String = "",
    val lastNameError: String? = null,
    val email: String = "",
    val emailError: String? = null,
    val password: String = "",
    val passwordError: String? = null,
    val confirmPassword: String = "",
    val confirmPasswordError: String? = null,
    val isLoading: Boolean = false,
    val extraText: String? = null,
    val hasError : Boolean = false,
    val isSigneUp : Boolean = false,
    val apiErrorMessageResId: String? = null,
    val isCompressingImage: Boolean = false
    
)
