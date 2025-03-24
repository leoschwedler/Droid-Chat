package com.example.droidchat.features.signup.presentation.validator

interface FormValidator<T> {
    fun validate(state: T): T
}