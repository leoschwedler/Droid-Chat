package com.example.droidchat.features.signup.presentation.di

import androidx.lifecycle.ViewModel
import com.example.droidchat.features.signup.presentation.state.SignUpState
import com.example.droidchat.features.signup.presentation.validator.FormValidator
import com.example.droidchat.features.signup.presentation.validator.SignUpFormValidator
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface FormValidatorModule {

    @Binds
    fun bindFormValidator(signUpFormValidator: SignUpFormValidator): FormValidator<SignUpState>
}