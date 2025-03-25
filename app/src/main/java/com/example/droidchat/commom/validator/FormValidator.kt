package com.example.droidchat.commom.validator

interface FormValidator<T> {
    fun validate(state: T): T
}