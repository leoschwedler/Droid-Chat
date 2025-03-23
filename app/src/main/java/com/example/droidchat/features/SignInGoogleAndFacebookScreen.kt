package com.example.droidchat.features

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.droidchat.R
import com.example.droidchat.commom.components.CommomButton
import com.example.droidchat.commom.components.PrimaryButton
import com.example.droidchat.commom.theme.BackgroundGradient
import com.example.droidchat.commom.extension.bottomBorder

@Composable
fun SignInGoogleAndFacebookScreen(
    naviteToSignUpScreen: () -> Unit,
    naviteToSignInScreen: () -> Unit,
) {
    var isLoading by remember { mutableStateOf(false) }

    SignInGoogleAndFacebookContent(
        isLoading = isLoading,
        naviteToSignUpScreen = naviteToSignUpScreen,
        naviteToSignInScreen = naviteToSignInScreen
    )
}

@Composable
private fun SignInGoogleAndFacebookContent(
    isLoading: Boolean,
    naviteToSignUpScreen: () -> Unit,
    naviteToSignInScreen: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundGradient)
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(145.dp))
        Image(
            painter = painterResource(R.drawable.logo),
            contentDescription = "Logo",
            modifier = Modifier
                .height(134.dp)
                .width(250.dp)
        )
        Text(
            modifier = Modifier.padding(top = 30.dp),
            text = "Esteja Sempre Conectado!",
            fontSize = 35.sp,
            color = Color.White,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            lineHeight = 40.sp
        )
        PrimaryButton(
            modifier = Modifier.padding(top = 80.dp),
            title = "Entrar com Google",
            icon = R.drawable.ic_safety,
        )
        PrimaryButton(
            modifier = Modifier.padding(top = 10.dp),
            title = "Entrar com facebook",
            icon = R.drawable.ic_safety,
        )
        CommomButton(
            title = "Começar",
            modifier = Modifier.padding(top = 50.dp),
            onClick = naviteToSignInScreen,
            isLoading = isLoading
        )
        Text(
            modifier = Modifier.padding(top = 50.dp).bottomBorder(color =  Color.White, strokeWidth =  2.dp),
            text = "Esqueci minha senha >>",
            color = Color.White,
            fontSize = 12.sp,)
        Spacer(modifier = Modifier.height(5.dp))

        Text(
            text = "Primeiro acesso >>",
            color = Color.White,
            fontSize = 12.sp,
            modifier = Modifier.bottomBorder(color =  Color.White, strokeWidth =  2.dp).clickable { naviteToSignUpScreen() }
            )

    }
}

@Preview(showBackground = true)
@Composable
private fun SignInGoogleAndFacebookPreview() {
    SignInGoogleAndFacebookContent(
        isLoading = false, naviteToSignUpScreen = {}, naviteToSignInScreen = {}
    )
}