package com.example.droidchat.features.SignInScreen.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.droidchat.features.SignInScreen.presentation.action.SignInAction
import com.example.droidchat.features.SignInScreen.presentation.state.SignInUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(SignInUiState())
    val uiState = _uiState.asStateFlow()

    fun onActions(action: SignInAction) {
        when (action) {

            is SignInAction.onEmailChange -> {
                _uiState.update { it.copy(email = action.email) }
            }

            is SignInAction.onPasswordChange -> {
                _uiState.update { it.copy(password = action.password) }
            }
            SignInAction.onSubmit -> {
               doSubmit()
            }
        }
    }
    fun doSubmit(){
        var isFormValid = true
        val email = _uiState.value.email
        val password = _uiState.value.password
        if (email.isBlank()){
            isFormValid = false
            _uiState.update { it.copy(isEmailError = true) }
        }
        if (password.isBlank()){
            isFormValid = false
            _uiState.update { it.copy(isPasswordError = true) }
        }
        if (isFormValid){
            _uiState.update { it.copy(isLoading = true) }
            _uiState.update { it.copy(email = "", isEmailError = false, password = "" , isPasswordError = false) }
        }
    }
}