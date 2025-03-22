package com.example.droidchat.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.droidchat.features.SignInGoogleAndFacebookScreen
import com.example.droidchat.features.SignInScreen.presentation.ui.SignInScreen
import com.example.droidchat.features.SignUpScreen
import com.example.droidchat.features.SplashScreen
import com.example.droidchat.navigation.MainRoutes.*
import com.example.droidchat.navigation.extension.slideInTo
import com.example.droidchat.navigation.extension.slideOutTo

@Composable
fun MainNavHost() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = SplashRoute) {
        composable<SplashRoute> {
            SplashScreen(
                navigateToSign = {
                    navController.navigate(route = SignInGoogleAndFacebookRoute) {
                        popUpTo(SplashRoute) { inclusive = true }
                    }
                }
            )
        }
        composable<SignInGoogleAndFacebookRoute>(
            enterTransition = {
                this.slideInTo(AnimatedContentTransitionScope.SlideDirection.Left)
            },
            exitTransition = {
                this.slideOutTo(AnimatedContentTransitionScope.SlideDirection.Right)
            }
        ) {
            SignInGoogleAndFacebookScreen(
                naviteToSignUpScreen = {
                    navController.navigate(SignUpRoute)
                },
                naviteToSignInScreen = {
                    navController.navigate(SignInRoute)
                }
            )
        }
        composable<SignInRoute>(
            enterTransition = {
                this.slideInTo(AnimatedContentTransitionScope.SlideDirection.Right)
            },
            exitTransition = {
                this.slideOutTo(AnimatedContentTransitionScope.SlideDirection.Left)
            }
        ) {
            SignInScreen(
                navigateToSignUp = {
                    navController.navigate(SignUpRoute)
                }
            )
        }
        composable<SignUpRoute> { SignUpScreen() }
        composable<LoginRoute> {}
    }
}