package com.example.droidchat.features

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
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
import coil.compose.AsyncImage
import com.example.droidchat.R
import com.example.droidchat.commom.components.BasicTextFieldCustom
import com.example.droidchat.commom.components.CommomButton
import com.example.droidchat.commom.theme.BackgroundGradient
import com.example.droidchat.commom.theme.Turquoise80
import com.example.droidchat.navigation.extension.bottomBorder

@Composable
fun SignUpScreen() {
    SignUpContent(imageUri = -1)
}

@Composable
fun SignUpContent(
    imageUri: Int
) {
    Column(
        Modifier
            .fillMaxSize()
            .background(BackgroundGradient),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(56.dp))
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
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.surface
        ) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(5.dp))
                AsyncImage(

                    model = painterResource(imageUri),
                    contentDescription = null,
                    placeholder = painterResource(R.drawable.ic_upload_photo),

                )
                Text("Adicionar foto")
                Spacer(Modifier.height(29.dp))
                BasicTextFieldCustom(
                    value = "Leonardo",
                    onValueChange = {},
                    label = "Primeiro Nome",
                    imeAction = ImeAction.Next
                )

                Spacer(Modifier.height(22.dp))
                BasicTextFieldCustom(
                    value = "Schwedler",
                    onValueChange = {},
                    label = "Ultimo Nome",
                    imeAction = ImeAction.Next
                )

                Spacer(Modifier.height(22.dp))
                BasicTextFieldCustom(
                    value = "DeveloperSchwedler@gmail.com",
                    onValueChange = {},
                    label = "Email",
                    imeAction = ImeAction.Next
                )

                Spacer(Modifier.height(22.dp))
                BasicTextFieldCustom(
                    value = "",
                    onValueChange = {},
                    keyboardType = KeyboardType.Password,
                    label = "Senha",
                    extraText = "as senhas sao iguais",
                    imeAction = ImeAction.Next
                )
                Spacer(Modifier.height(22.dp))
                BasicTextFieldCustom(
                    value = "",
                    onValueChange = {},
                    extraText = "as senhas sao iguais",
                    keyboardType = KeyboardType.Password,
                    label = "Confirmação de senha",
                    imeAction = ImeAction.Done
                )
                Spacer(Modifier.height(36.dp))
                CommomButton(title = "Cadastrar", onClick = {}, modifier = Modifier.height(64.dp))
                Spacer(Modifier.height(16.dp))
                Row (modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                    Text(text = "Possui Conta")
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = "Faça login aqui", color = Turquoise80, modifier =  Modifier.bottomBorder(strokeWidth = 1.dp, color = Turquoise80))
                }
            }
        }

    }
}

@Preview
@Composable
private fun SignUpPreview() {
    SignUpContent(-1)
}