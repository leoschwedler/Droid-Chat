package com.example.droidchat.features.signin.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.droidchat.commom.data.repository.AuthRepository
import com.example.droidchat.commom.domain.model.LoginAccount
import com.example.droidchat.commom.util.NetworkException
import com.example.droidchat.commom.validator.FormValidator
import com.example.droidchat.features.signin.presentation.action.SignInAction
import com.example.droidchat.features.signin.presentation.state.SignInState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val formValidator: FormValidator<SignInState>
) :
    ViewModel() {

    private val _uiState = MutableStateFlow(SignInState())
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
                onSubmit()
            }
        }
    }

    fun onSubmit() {
        if (isValidForm()) {
            _uiState.update { it.copy(isLoading = true) }
            viewModelScope.launch {
                val request = LoginAccount(
                    username = _uiState.value.email,
                    password = _uiState.value.password
                )
                authRepository.signIn(request).fold(
                    onSuccess = {
                        _uiState.update { it.copy(isLoading = false) }

                        _uiState.update { it.copy(isSignedIn = true) }
                    },
                    onFailure = { exception ->
                        if (exception is NetworkException.ApiException) {
                            when (exception.statusCode) {
                                401 -> {
                                    _uiState.update { it.copy(apiErrorMessageResId = "Usuário ou senha incorretos", isLoading = false)
                                    }
                                }
                            }
                        } else {
                            _uiState.update { it.copy(apiErrorMessageResId = "Alguma coisa deu errado",isLoading = false) }
                        }
                    }
                )
            }
        }
    }

    fun isValidForm(): Boolean {
        val validateState = formValidator.validate(_uiState.value)
        _uiState.update { validateState }
        return !validateState.hasError
    }


    fun onDismissDialog() {
        _uiState.update { it.copy(apiErrorMessageResId = null) }
    }

}