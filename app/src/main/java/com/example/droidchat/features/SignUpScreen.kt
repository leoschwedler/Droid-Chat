package com.example.droidchat.features

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.droidchat.R
import com.example.droidchat.commom.components.BasicTextFieldCustom
import com.example.droidchat.commom.theme.BackgroundGradient

@Composable
fun SignUpScreen() {

}

@Composable
fun SignUpContent() {
    Column(
        Modifier
            .fillMaxSize()
            .background(BackgroundGradient),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(50.dp))
        Image(
            painter = painterResource(R.drawable.logo),
            contentDescription = null,
            modifier = Modifier
                .height(135.dp)
                .width(255.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Surface(
            shape = RoundedCornerShape(topStart = 50.dp, topEnd = 50.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_upload_photo),
                    contentDescription = null
                )
                Text("Adicionar foto")
                Spacer(Modifier.height(30.dp))
                BasicTextFieldCustom(
                    value = "Leonardo",
                    onValueChange = {},
                    label = "Primeiro Nome",
                    imeAction = ImeAction.Next
                )

                Spacer(Modifier.height(15.dp))
                BasicTextFieldCustom(
                    value = "Schwedler",
                    onValueChange = {},
                    label = "Ultimo Nome",
                    imeAction = ImeAction.Next
                )

                Spacer(Modifier.height(15.dp))
                BasicTextFieldCustom(
                    value = "DeveloperSchwedler@gmail.com",
                    onValueChange = {},
                    label = "Email",
                    imeAction = ImeAction.Next
                )

                Spacer(Modifier.height(15.dp))
                BasicTextFieldCustom(
                    value = "",
                    onValueChange = {},
                    keyboardType = KeyboardType.Password,
                    label = "Senha",
                    extraText = "as senhas sao iguais",
                    imeAction = ImeAction.Next
                )
                Spacer(Modifier.height(15.dp))
                BasicTextFieldCustom(
                    value = "",
                    onValueChange = {},
                    extraText = "as senhas sao iguais",
                    keyboardType = KeyboardType.Password,
                    label = "Confirmação de senha",
                    imeAction = ImeAction.Next
                )

            }
        }

    }
}

@Preview
@Composable
private fun SignUpPreview() {
    SignUpContent()
}