package com.example.droidchat.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.droidchat.navigation.MainRoutes.*
import com.example.droidchat.ui.features.SignInGoogleAndFacebookScreen
import com.example.droidchat.ui.features.SignInScreen
import com.example.droidchat.ui.features.SplashScreen

@Composable
fun MainNavHost() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = SplashRoute) {
        composable<SplashRoute> {
            SplashScreen(
                navigateToSign = {
                    navController.navigate(route = SignInGoogleAndFacebookRoute){
                        popUpTo(SplashRoute) { inclusive = true }
                    }
                }
            )
        }
        composable<LoginRoute> {

        }
        composable<SignInRoute> {
            SignInScreen()
        }
        composable<SignInGoogleAndFacebookRoute>{
            SignInGoogleAndFacebookScreen(
                naviteToSignUpScreen = {
                    navController.navigate(route = SignInRoute)
                }
            )
        }
    }
}