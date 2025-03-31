package com.example.droidchat.features.signup.presentation.viewmodel

import android.net.Uri
import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.droidchat.commom.data.repository.AuthRepository
import com.example.droidchat.commom.domain.model.CreateAccount
import com.example.droidchat.commom.util.NetworkException
import com.example.droidchat.commom.util.image.ImageCompressor
import com.example.droidchat.commom.validator.FormValidator
import com.example.droidchat.features.signup.presentation.action.SignUpUiAction
import com.example.droidchat.features.signup.presentation.event.SignUpEvent
import com.example.droidchat.features.signup.presentation.state.SignUpState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
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
    private val authRepository: AuthRepository,
    private val imageCompressor: ImageCompressor,
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
                action.uri?.let { compressImageAndUpdateState(it) }
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
            viewModelScope.launch(Dispatchers.IO) {
                var profilePicutreId: Int? = null
                var errorWhenUploadProfilePicture = false

                _uiState.value.profilePictureUri?.path?.let { path ->
                    authRepository.profilePicture(path).fold(
                        onSuccess = {
                            profilePicutreId = it.id
                        },
                        onFailure = {
                            errorWhenUploadProfilePicture = true
                            _uiState.update { it.copy(profilePictureUri = null) }
                            _uiState.update { it.copy(apiErrorMessageResId = "Error ao fazer upload da imagem de perfil") }
                        }
                    )
                }
                if (errorWhenUploadProfilePicture) {
                    return@launch
                }

                val request = CreateAccount(
                    firstName = _uiState.value.firstName,
                    lastName = _uiState.value.lastName,
                    password = _uiState.value.password,
                    profilePictureId = profilePicutreId,
                    username = _uiState.value.email
                )
                authRepository.signUp(request).fold(
                    onSuccess = {
                        _uiState.update { it.copy(isLoading = false) }
                        _uiState.update { it.copy(isSigneUp = true) }
                    },
                    onFailure = {
                        _uiState.update { it.copy(isLoading = false) }
                        if (it is NetworkException.ApiException) {
                            when (it.statusCode) {
                                409 -> {
                                    _uiState.update { it.copy(apiErrorMessageResId = "Erro de validação de formulário, confira os dados e tente novamente") }
                                }

                                400 -> {
                                    _uiState.update { it.copy(apiErrorMessageResId = "Usuário com o e-mail fornecido já existe no sistema") }
                                }

                                else -> {
                                    _uiState.update { it.copy(apiErrorMessageResId = "Alguma coisa deu errado") }
                                }
                            }
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

    private fun compressImageAndUpdateState(uri: Uri) {
        viewModelScope.launch {
            try {
                _uiState.update { it.copy(isCompressingImage = true) }
                val compressedImage = imageCompressor.compressAndResizeImage(uri)
                _uiState.update { it.copy(profilePictureUri = compressedImage.toUri()) }
            } catch (e: Exception) {
            } finally {
                _uiState.update { it.copy(isCompressingImage = false) }
            }
        }
    }
}