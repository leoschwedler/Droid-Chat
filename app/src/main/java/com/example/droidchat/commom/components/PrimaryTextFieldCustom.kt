package com.example.droidchat.commom.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.droidchat.R
import com.example.droidchat.commom.theme.DroidChatTheme
import com.example.droidchat.commom.theme.Turquoise80

@Composable
fun PrimaryTextFieldCustom(
    leadingIcon: Int,
    keyboardType: KeyboardType,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    isError: String? = null,
    imeAction: ImeAction,
    modifier: Modifier = Modifier
) {

    var visibilityPassword by remember { mutableStateOf(false) }

    OutlinedTextField(
        modifier = modifier.fillMaxWidth(),
        singleLine = true,
        leadingIcon = {
            Icon(
                modifier = Modifier.padding(start = 16.dp),
                painter = painterResource(leadingIcon),
                contentDescription = null,
                tint =  Turquoise80
            )
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = imeAction
        ),
        visualTransformation = if (keyboardType == KeyboardType.Password && visibilityPassword == true) {
            PasswordVisualTransformation()
        } else {
            VisualTransformation.None
        },
        trailingIcon = {
            if (keyboardType == KeyboardType.Password && value.isNotBlank()) {
                val visibility = if (visibilityPassword == true) {
                    R.drawable.ic_visibility
                } else {
                    R.drawable.ic_visibility_off
                }
                Icon(
                    modifier = Modifier
                        .padding(end = 16.dp)
                        .clickable {
                            visibilityPassword = !visibilityPassword
                        },
                    painter = painterResource(visibility),
                    contentDescription = null,
                    tint =  Turquoise80
                )
            }
        },
        placeholder = {
            Text(placeholder)
        },
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
            focusedContainerColor = MaterialTheme.colorScheme.surface,
            disabledContainerColor = MaterialTheme.colorScheme.surface,
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor =  Turquoise80
        ),
        value = value,
        onValueChange = onValueChange,
        shape = MaterialTheme.shapes.extraLarge
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun PrimaryTextField() {
    DroidChatTheme {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            PrimaryTextFieldCustom(
                value = "Leo",
                leadingIcon = R.drawable.ic_envelope,
                placeholder = "Email",
                keyboardType = KeyboardType.Password,
                onValueChange = {},
                modifier = Modifier.padding(horizontal = 16.dp),
                imeAction = ImeAction.Done,
            )
        }
    }
}