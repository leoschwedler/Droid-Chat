package com.example.droidchat.commom.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun AlertDialogCustom(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit,
    onConfirmButton: () -> Unit,
    title: String? = null,
    text: String,
    confirmButtonText: String = "OK"
) {
    AlertDialog(
        modifier = modifier,
        onDismissRequest = onDismissRequest,
        confirmButton = {
            TextButton(onClick = onConfirmButton) {
                Text(text = confirmButtonText)
            }
        },
        title = {
            title?.let {
                Text(text = it)
            }
        },
        text = {
            Text(text = text)
        }
    )
}

@Preview
@Composable
private fun AlertDialogCustomPreview() {

    AlertDialogCustom(
        onDismissRequest = {},
        onConfirmButton = {},
        text = "teste",
        title = "teste"
    )

}