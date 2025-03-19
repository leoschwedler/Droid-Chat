package com.example.droidchat.commom.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.droidchat.commom.theme.Turquoise80

@Composable
fun CommomButton(
    onClick: () -> Unit,
    title: String,
    modifier: Modifier = Modifier
) {
    Button(
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(16.dp),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Turquoise80
        )
    ) {
        Text(text = title, fontWeight = FontWeight.Bold)
    }
}

@Preview
@Composable
private fun CommomButtonPreview() {
    CommomButton( title = "", onClick = {})
}