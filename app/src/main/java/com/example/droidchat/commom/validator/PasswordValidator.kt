package com.example.droidchat.commom.validator

object PasswordValidator {

    fun isValid(value: String): Boolean{
        return value.length >= 6 && value.any { it.isDigit() } && value.any { it.isLetter() }
    }
}