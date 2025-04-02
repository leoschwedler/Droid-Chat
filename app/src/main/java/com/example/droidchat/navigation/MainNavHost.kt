package com.example.droidchat.navigation

import android.widget.Toast
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.droidchat.MainActivity
import com.example.droidchat.commom.extension.slideInTo
import com.example.droidchat.commom.extension.slideOutTo
import com.example.droidchat.features.HomeScreen
import com.example.droidchat.features.SignInGoogleAndFacebookScreen
import com.example.droidchat.features.signin.presentation.ui.SignInScreen
import com.example.droidchat.features.signup.presentation.ui.SignUpScreen
import com.example.droidchat.navigation.MainRoutes.*

@Composable
fun MainNavHost() {
    val navController = rememberNavController()
    val activity = LocalContext.current as MainActivity
    NavHost(navController = navController, startDestination = SplashRoute) {
        composable<SplashRoute> {
            SplashScreen(
                navigateToHome = {
                    Toast.makeText(navController.context, "Usuário logado com sucesso", Toast.LENGTH_SHORT).show()
                    navController.navigate(route = HomeRoute) {
                        popUpTo(SplashRoute) { inclusive = true }
                    }
                },
                navigateToSignIn = {
                    navController.navigate(SignInRoute)
                },
                onCloseApp = {
                    activity.finish()
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
            val context = LocalContext.current
            SignInScreen(
                navigateToSignUp = {
                    navController.navigate(SignUpRoute)
                },
                navigateToHome = {
                    Toast.makeText(context, "Logado com sucesso", Toast.LENGTH_SHORT).show()
                    navController.navigate(HomeRoute)
                }
            )
        }
        composable<SignUpRoute> {
            SignUpScreen(
                onSignUpSuccess = {
                    navController.popBackStack()
                }
            )
        }
        composable<HomeRoute> {
            HomeScreen()
        }
    }
}