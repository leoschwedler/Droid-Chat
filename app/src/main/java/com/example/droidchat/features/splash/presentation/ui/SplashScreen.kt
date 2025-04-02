package com.example.droidchat.features.splash.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LifecycleStartEffect
import com.example.droidchat.R
import com.example.droidchat.commom.components.AlertDialogCustom
import com.example.droidchat.commom.theme.BackgroundGradient
import com.example.droidchat.commom.theme.DroidChatTheme
import com.example.droidchat.commom.theme.Turquoise80
import com.example.droidchat.features.splash.presentation.event.SplashEvent
import com.example.droidchat.features.splash.presentation.viewmodel.SplashViewModel

@Composable
fun SplashScreen(
    viewmodel: SplashViewModel = hiltViewModel(),
    navigateToHome: () -> Unit,
    navigateToSignIn: () -> Unit,
    onCloseApp: () -> Unit
) {

    val uiState by viewmodel.showErrorDialog.collectAsState()

    LifecycleStartEffect(Unit) {
        viewmodel.checkSession()
        onStopOrDispose { }
    }

    LaunchedEffect(Unit) {
        viewmodel.splashEvent.collect { event ->
            when (event) {
                SplashEvent.UserAuthenticated -> {
                     navigateToHome()
                }
                SplashEvent.UserNotAuthenticated -> {
                    navigateToSignIn()
                }
            }
        }
    }

    if (uiState){
        AlertDialogCustom(
            onDismissRequest =  onCloseApp ,
            onConfirmButton =  onCloseApp ,
            confirmButtonText = stringResource(R.string.fechar_app),
            text = stringResource(R.string.sess_o_expirada_fa_a_login_novamente)
        )
    }

    SplashContent()

}

@Composable
private fun SplashContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundGradient),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier
                .height(134.dp)
                .width(250.dp),
            painter = painterResource(R.drawable.logo), contentDescription = null
        )
        Row(
            Modifier
                .fillMaxWidth()
                .padding(top = 50.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_safety),
                contentDescription = null,
                tint = Turquoise80
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Mensagens, Seguras Criptografadas e Privadas",
                color = Color.White,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SplashPreview() {
    DroidChatTheme {
        SplashContent()
    }
}