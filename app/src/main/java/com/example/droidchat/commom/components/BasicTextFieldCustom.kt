package com.example.droidchat.commom.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.droidchat.R
import com.example.droidchat.commom.theme.ColorSuccess
import com.example.droidchat.commom.theme.DroidChatTheme
import com.example.droidchat.navigation.extension.bottomBorder

@Composable
fun BasicTextFieldCustom(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    extraText: String? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction,
    modifier: Modifier = Modifier
) {

    var passwordVisibility by remember { mutableStateOf(false) }


    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        minLines = 1,
        singleLine = true,
        modifier = modifier,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = imeAction
        ),
        visualTransformation = if (keyboardType == KeyboardType.Password) {
            if (passwordVisibility) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            }
        } else {
            VisualTransformation.None
        }
    ) { innerTextField ->
        Surface(color = MaterialTheme.colorScheme.surface) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth().bottomBorder(color = Color.Black, strokeWidth = 2.dp)
            ) {
                Column (Modifier.fillMaxWidth().weight(1f)){
                    Text(
                        label,
                        style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(Modifier.height(4.dp))
                    Row (verticalAlignment = Alignment.CenterVertically,){
                        Box(modifier = Modifier.weight(1f)) {
                            innerTextField()
                        }
                        extraText?.let {
                            Text(
                                it,
                                modifier = Modifier.padding(4.dp),
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.Bold,
                                color = ColorSuccess
                            )
                        }
                    }
                }
                if (keyboardType == KeyboardType.Password && value.isNotBlank()){
                    val visibilityIcon = if (passwordVisibility){
                        R.drawable.ic_visibility

                    } else {
                        R.drawable.ic_visibility_off
                    }
                    IconButton(onClick = { passwordVisibility = !passwordVisibility}) {
                        Icon(painter = painterResource(visibilityIcon), contentDescription = null)
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun BasicTextFieldCustomPreview() {
    DroidChatTheme {
        Box(modifier = Modifier.fillMaxSize()) {
            BasicTextFieldCustom(
                modifier = Modifier.align(Alignment.Center),
                value = "",
                onValueChange = {},
                label = "Password",
                extraText = "Password matches",
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Next
            )
        }
    }
}