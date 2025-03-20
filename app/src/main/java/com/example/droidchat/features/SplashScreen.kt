package com.example.droidchat.features

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.droidchat.R
import com.example.droidchat.commom.theme.BackgroundGradient
import com.example.droidchat.commom.theme.DroidChatTheme
import com.example.droidchat.commom.theme.Turquoise80
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navigateToSign: () -> Unit,
) {
    SplashContent()
    LaunchedEffect(Unit) {
        delay(3000)
        navigateToSign()
    }
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