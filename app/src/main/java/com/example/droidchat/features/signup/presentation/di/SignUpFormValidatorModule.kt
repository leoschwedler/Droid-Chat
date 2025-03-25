package com.example.droidchat.features.signup.presentation.di

import com.example.droidchat.features.signup.presentation.state.SignUpState
import com.example.droidchat.commom.validator.FormValidator
import com.example.droidchat.features.signup.presentation.validator.SignUpFormValidator
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(ViewModelComponent::class)
interface SignUpFormValidatorModule {

    @Binds
    fun bindFormValidator(signUpFormValidator: SignUpFormValidator): FormValidator<SignUpState>
}