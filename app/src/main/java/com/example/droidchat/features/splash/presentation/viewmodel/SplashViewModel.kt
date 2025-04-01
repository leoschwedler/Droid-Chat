package com.example.droidchat.features.splash.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.droidchat.commom.data.repository.AuthRepository
import com.example.droidchat.features.splash.presentation.event.SplashEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val authRepository: AuthRepository) :
    ViewModel() {

    private val _showErrorDialog = MutableStateFlow(false)
    val showErrorDialog = _showErrorDialog.asStateFlow()

    private val _splashEvent = Channel<SplashEvent>()
    val splashEvent = _splashEvent.receiveAsFlow()

    fun checkSession(){
        closeErrorDialog()
        viewModelScope.launch {
            val accessToken = authRepository.getAccessToken()
            if (accessToken.isNullOrBlank()){
                _splashEvent.send(SplashEvent.UserNotAuthenticated)
                return@launch
            }
            authRepository.authenticate(accessToken).fold(
                onSuccess = {
                    _splashEvent.send(SplashEvent.UserAuthenticated)
                },
                onFailure = {
                    _showErrorDialog.update { true }
                }
            )
        }
    }

     fun closeErrorDialog() {
        _showErrorDialog.update { false }
    }
}