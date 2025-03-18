package com.example.droidchat.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.droidchat.navigation.MainRoutes.*
import com.example.droidchat.ui.features.SignInScreen
import com.example.droidchat.ui.features.SplashScreen

@Composable
fun MainNavHost() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = SplashRoute) {
        composable<SplashRoute> {
            SplashScreen(
                navigateToSign = {
                    navController.navigate(route = SignInRoute, popUpto(SplashRoute))
                }
            )
        }
        composable<LoginRoute> {

        }
        composable<SignInRoute> {
            SignInScreen()
        }
    }
}