package com.example.droidchat.commom.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.droidchat.commom.theme.DroidChatTheme

@Composable
fun PrimaryButton(
    title: String,
    icon: Int,
    iconPassword: Int? = null,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = MaterialTheme.shapes.extraLarge,
        color = Color.White,
        modifier = modifier.fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .height(50.dp)
                .fillMaxWidth().padding(horizontal = 26.dp)
        ) {

            Text(
                text = title,
                fontSize = 16.sp,
                color = Color(0xFF434343),
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.Center)
            )

            Image(
                painter = painterResource(icon),
                contentDescription = null,
                modifier = Modifier.align(Alignment.CenterStart)

            )
            iconPassword?.let {
                Image(
                    painter = painterResource(it),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                )
            }
        }
    }
}


@Preview
@Composable
private fun PrimaryButtonPreview() {
    DroidChatTheme {
        PrimaryButton(
            title = "Entrar",
            icon = R.drawable.ic_safety
        )

    }
}