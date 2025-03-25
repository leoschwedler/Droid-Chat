package com.example.droidchat.features.signin.presentation.di

import com.example.droidchat.commom.validator.FormValidator
import com.example.droidchat.features.signin.presentation.state.SignInState
import com.example.droidchat.features.signin.presentation.validator.SignInFormValidator
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface SignInFormValidator {


    @Binds
    fun bindSignInFormValidator(signInFormValidator: SignInFormValidator): FormValidator<SignInState>
}