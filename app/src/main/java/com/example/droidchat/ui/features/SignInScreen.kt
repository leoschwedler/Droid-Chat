package com.example.droidchat.ui.features

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.droidchat.R
import com.example.droidchat.commom.components.CustomButton
import com.example.droidchat.commom.theme.BackgroundGradient
import com.example.droidchat.commom.theme.DroidChatTheme

@Composable
fun SignInScreen() {
    SignInContent()
}

@Composable
private fun SignInContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundGradient)
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(50.dp))
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
            fontWeight = FontWeight.Bold,
            lineHeight = 40.sp
        )
        CustomButton(
            modifier = Modifier.padding(top = 80.dp),
            title = "Entrar com Google",
            icon = R.drawable.ic_safety,
        )
        CustomButton(
            modifier = Modifier.padding(top = 10.dp),
            title = "Entrar com facebook",
            icon = R.drawable.ic_safety,
        )
        Button(
            modifier = Modifier.fillMaxWidth().padding(top = 50.dp),
            contentPadding = PaddingValues(16.dp),
            onClick = {}
        ) {
            Text(text = "Começar", fontWeight = FontWeight.Bold)
        }
        Text(
            modifier = Modifier.padding(top = 50.dp),
            text = "Esqueci mninha senha >>",
            color = Color.White,
            fontSize = 12.sp,

        )
        Divider(modifier = Modifier.padding(horizontal = 105.dp))
        Text(
            text = "Primeiro acesso >>",
            color = Color.White,
            fontSize = 12.sp,

            )
        Divider(modifier = Modifier.padding(horizontal = 125.dp))
    }
}

@Preview(showBackground = true)
@Composable
private fun SignupPreview() {
    DroidChatTheme {
        SignInContent()
    }
}