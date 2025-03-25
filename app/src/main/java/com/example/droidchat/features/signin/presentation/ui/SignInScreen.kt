package com.example.droidchat.features.signin.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.droidchat.R
import com.example.droidchat.commom.components.CommomButton
import com.example.droidchat.commom.components.PrimaryTextFieldCustom
import com.example.droidchat.commom.theme.BackgroundGradient
import com.example.droidchat.commom.theme.Turquoise80
import com.example.droidchat.features.signin.presentation.action.SignInAction
import com.example.droidchat.features.signin.presentation.state.SignInState
import com.example.droidchat.features.signin.presentation.viewmodel.SignInViewModel

@Composable
fun SignInScreen(
    navigateToSignUp: () -> Unit,
    hiltViewModel: SignInViewModel = hiltViewModel()
) {
    val uiState by hiltViewModel.uiState.collectAsState()



    SignInContent(
        uiState = uiState,
        onActions = hiltViewModel::onActions,
        navigateToSignUp = navigateToSignUp
    )


}

@Composable
fun SignInContent(
    uiState: SignInState,
    onActions: (SignInAction) -> Unit,
    navigateToSignUp: () -> Unit
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
            value = uiState.email,
            placeholder = "Developer@gmail.com",
            isError = uiState.emailErrorMessage,
            onValueChange = {
                onActions(SignInAction.onEmailChange(it))
            },
            imeAction = ImeAction.Next
        )
        Spacer(modifier = Modifier.height(15.dp))
        PrimaryTextFieldCustom(
            leadingIcon = R.drawable.ic_lock,
            keyboardType = KeyboardType.Password,
            value = uiState.password,
            placeholder = "*********",
            isError = uiState.passwordErrorMessage,
            onValueChange = {
                onActions(SignInAction.onPasswordChange(it))
            },
            imeAction = ImeAction.Done,
        )
        Spacer(modifier = Modifier.height(70.dp))
        CommomButton(
            title = "Entrar",
            onClick = {
                onActions(SignInAction.onSubmit)
            },
            isLoading = uiState.isLoading
        )
        Spacer(modifier = Modifier.height(50.dp))
        Row {
            Text(text = "Não possui conta?", color = Color.White)
            Spacer(modifier = Modifier.width(3.dp))
            Text(
                text = "Cadastre-se aqui",
                color = Turquoise80,
                modifier = Modifier.clickable { navigateToSignUp() })
        }
    }
}

@Preview
@Composable
private fun SignInScreenPreview() {
    SignInScreen({})
}