package com.example.droidchat.commom.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.droidchat.commom.theme.Turquoise80

@Composable
fun CommomButton(
    title: String,
    isLoading: Boolean = false,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {


    Button(
        modifier = modifier,
        enabled = !isLoading,
        contentPadding = PaddingValues(16.dp),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Turquoise80,
            disabledContainerColor = Turquoise80.copy(alpha = 0.8f)
        )
    ) {
        Box (modifier = Modifier.animateContentSize()){
            if (isLoading) {
                CircularProgressIndicator(
                    color = Color.White,
                    modifier = Modifier.size(32.dp),
                    strokeCap = StrokeCap.Round
                )
            } else {
                Text(
                    text = title,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Preview
@Composable
private fun CommomButtonPreview() {
    CommomButton(title = "Sign Up", onClick = {}, isLoading = false, )
}