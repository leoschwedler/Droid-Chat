package com.example.droidchat.commom.components

import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun BasicTextFieldCustom(modifier: Modifier = Modifier) {
    BasicTextField(
        value = "",
        onValueChange = {}
    )
}

@Preview(showBackground = true)
@Composable
private fun BasicTextFieldCustomPreview() {
    BasicTextFieldCustom()
}