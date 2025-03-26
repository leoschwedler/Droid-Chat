package com.example.droidchat.features.signup.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.droidchat.commom.data.repository.AuthRepository
import com.example.droidchat.commom.domain.model.CreateAccount
import com.example.droidchat.commom.domain.model.NetworkException
import com.example.droidchat.commom.validator.FormValidator
import com.example.droidchat.features.signup.presentation.action.SignUpUiAction
import com.example.droidchat.features.signup.presentation.event.SignUpEvent
import com.example.droidchat.features.signup.presentation.state.SignUpState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val formValidator: FormValidator<SignUpState>,
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(SignUpState())
    val uiState = _uiState.asStateFlow()

    private val _uiEvent = Channel<SignUpEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onActions(action: SignUpUiAction) {
        when (action) {
            is SignUpUiAction.onEmailChange -> {
                _uiState.update { it.copy(email = action.email) }
            }

            is SignUpUiAction.onLastNameChange -> {
                _uiState.update { it.copy(lastName = action.lastName) }
            }

            is SignUpUiAction.onNameChange -> {
                _uiState.update { it.copy(firstName = action.name) }
            }

            is SignUpUiAction.onPasswordChange -> {
                _uiState.update { it.copy(password = action.password) }
            }

            is SignUpUiAction.onBottomSheetChange -> {
                _uiState.update { it.copy(bottomSheet = action.bottomSheet) }
            }

            is SignUpUiAction.onConfirmPasswordChange -> {
                _uiState.update { it.copy(confirmPassword = action.confirmPassword) }
            }

            is SignUpUiAction.onProfilePictureSelected -> {
                _uiState.update { it.copy(profilePictureUri = action.uri) }
            }

            SignUpUiAction.onSubmit -> {
                onSubmit()
            }

            SignUpUiAction.onExtraTextChange -> {
                onExtraTextChange()
            }
        }
    }

    fun onExtraTextChange() {
        if (_uiState.value.password.isNotBlank() && _uiState.value.password == uiState.value.confirmPassword) {
            _uiState.update { it.copy(extraText = "as senhas sao iguais") }
        } else {
            _uiState.update { it.copy(extraText = null) }
        }
    }

    fun onSubmit() {
        if (isValidForm()) {
            _uiState.update { it.copy(isLoading = true) }
            viewModelScope.launch {
                try {
                    val request = CreateAccount(
                        firstName = _uiState.value.firstName,
                        lastName = _uiState.value.lastName,
                        password = _uiState.value.password,
                        profilePictureId = null,
                        username = _uiState.value.email
                    )
                    authRepository.signUp(request)
                    _uiEvent.send(SignUpEvent.showSnackBar("$request"))
                } catch (e: Exception) {
                    e.printStackTrace()
                    if (e is NetworkException.ApiException) {

                    } else {

                    }
                }
            }
        }
    }

    fun isValidForm(): Boolean {
        val validateState = formValidator.validate(_uiState.value)
        _uiState.update { validateState }
        return !validateState.hasError
    }
}