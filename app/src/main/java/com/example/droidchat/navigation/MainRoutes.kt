package com.example.droidchat.navigation

import kotlinx.serialization.Serializable

sealed class MainRoutes {

    @Serializable
    data object SplashRoute : MainRoutes()
    @Serializable
    data object LoginRoute : MainRoutes()
    @Serializable
    data object SignInRoute : MainRoutes()
    @Serializable
    data object SignInGoogleAndFacebookRoute: MainRoutes()
}