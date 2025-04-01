package com.example.droidchat.features.splash.presentation.event

sealed interface SplashEvent {
    data object UserAuthenticated : SplashEvent
    data object UserNotAuthenticated : SplashEvent
}