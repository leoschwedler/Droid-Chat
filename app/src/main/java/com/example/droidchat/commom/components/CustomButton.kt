package com.example.droidchat.commom.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import com.example.droidchat.commom.theme.DroidChatTheme

@Composable
fun CustomButton(
    title: String,
    icon: Int,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = MaterialTheme.shapes.extraLarge,
        color = Color.White,
        modifier = modifier
            .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .height(50.dp)
                .padding(start = 24.dp)
        ) {
            Image(painter = painterResource(icon), contentDescription = null)
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = title,
                fontSize = 16.sp,
                color = Color(0xFF434343),
                modifier = Modifier.padding(start = 10.dp),
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview
@Composable
private fun CustomButtonPreview() {
    DroidChatTheme {
        CustomButton(
            title = "Entrar",
            icon = R.drawable.ic_safety
        )
    }
}