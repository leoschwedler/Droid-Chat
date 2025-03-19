package com.example.droidchat.ui.features

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.droidchat.R
import com.example.droidchat.commom.components.CommomButton
import com.example.droidchat.commom.components.PrimaryTextFieldCustom
import com.example.droidchat.commom.theme.BackgroundGradient
import com.example.droidchat.commom.theme.Turquoise80

@Composable
fun SignInScreen() {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var visibilityPassword by remember { mutableStateOf(false) }

    SignInContent(email = email, password = password, onEmailChange = {
        email = it
    }, onPasswordChange = {
        password = it
    },
        visibilityPassword = visibilityPassword,
        onVisibilityPassword = {
            visibilityPassword = !visibilityPassword
        })

}

@Composable
fun SignInContent(
    email: String,
    password: String,
    visibilityPassword: Boolean,
    onVisibilityPassword: () -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
) {
    Column(
        Modifier
            .fillMaxSize()
            .background(BackgroundGradient)
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(145.dp))
        Image(
            painter = painterResource(R.drawable.logo),
            contentDescription = null,
            modifier = Modifier
                .height(135.dp)
                .width(255.dp)
        )
        Spacer(modifier = Modifier.height(50.dp))
        PrimaryTextFieldCustom(
            leadingIcon = R.drawable.ic_envelope,
            keyboardType = KeyboardType.Email,
            value = email,
            placeholder = "Developer@gmail.com",
            isError = false,
            onValueChange = onEmailChange,
            imeAction = ImeAction.Next
        )
        Spacer(modifier = Modifier.height(15.dp))
        PrimaryTextFieldCustom(
            leadingIcon = R.drawable.ic_lock,
            keyboardType = KeyboardType.Password,
            value = password,
            placeholder = "*********",
            isError = false,
            onValueChange = onPasswordChange,
            imeAction = ImeAction.Done,
            visibilityPassword = visibilityPassword,
            onVisibilityPassword = onVisibilityPassword
        )
        Spacer(modifier = Modifier.height(70.dp))
        CommomButton(title = "Entrar", onClick = {})
        Spacer(modifier = Modifier.height(50.dp))
        Row {
            Text(text = "Não possui conta?", color = Color.White)
            Spacer(modifier = Modifier.width(3.dp))
            Text(text = "Cadastre-se aqui", color = Turquoise80)
        }
    }
}

@Preview
@Composable
private fun SignInScreenPreview() {
    SignInScreen()
}