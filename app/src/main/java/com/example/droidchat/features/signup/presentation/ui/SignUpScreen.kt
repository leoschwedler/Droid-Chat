package com.example.droidchat.features.signup.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.droidchat.R
import com.example.droidchat.commom.components.AlertDialogCustom
import com.example.droidchat.commom.components.BasicTextFieldCustom
import com.example.droidchat.commom.components.CommomButton
import com.example.droidchat.commom.components.ProfilePictureOptionsModalBottomSheet
import com.example.droidchat.commom.extension.bottomBorder
import com.example.droidchat.commom.theme.BackgroundGradient
import com.example.droidchat.commom.theme.Turquoise80
import com.example.droidchat.features.signup.presentation.action.SignUpUiAction
import com.example.droidchat.features.signup.presentation.event.SignUpEvent
import com.example.droidchat.features.signup.presentation.state.SignUpState
import com.example.droidchat.features.signup.presentation.viewmodel.SignUpViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Composable
fun SignUpScreen(
    viewModel: SignUpViewModel = hiltViewModel(),
    onSignUpSuccess: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    val snackBarHostState = remember { SnackbarHostState() }
    LaunchedEffect(Unit) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                SignUpEvent.navigate -> {}
                is SignUpEvent.showSnackBar -> {
                    snackBarHostState.showSnackbar(message = event.message)
                }
            }
        }
    }
    if (uiState.isSigneUp){
        AlertDialogCustom(
            onDismissRequest = onSignUpSuccess,
            onConfirmButton = onSignUpSuccess,
            title = "Usuário criado com sucesso",
            text = "Seja bem vindo ao DroidChat",
        )
    }

    uiState.apiErrorMessageResId?.let { errorMessage ->
        AlertDialogCustom(
            onDismissRequest = viewModel::onDismissDialog,
            onConfirmButton = viewModel::onDismissDialog,
            title = "Alguma coisa deu errado",
            text = errorMessage,
        )
    }


    Box(Modifier.fillMaxSize()) {
        SignUpContent(
            uiState = uiState,
            onAction = viewModel::onActions
        )
        SnackbarHost(
            hostState = snackBarHostState,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 32.dp)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpContent(
    uiState: SignUpState,
    onAction: (SignUpUiAction) -> Unit,
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
                Box(contentAlignment = Alignment.Center) {
                    AsyncImage(
                        model = uiState.profilePictureUri ?: R.drawable.ic_upload_photo,
                        contentDescription = null,
                        placeholder = painterResource(R.drawable.ic_upload_photo),
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(80.dp)
                            .clip(CircleShape)
                            .clickable {
                                onAction(SignUpUiAction.onBottomSheetChange(true))
                            }
                    )
                    if (uiState.isCompressingImage) {
                        CircularProgressIndicator()
                    }

                }
                Spacer(Modifier.height(4.dp))
                val text = if (uiState.isCompressingImage) {
                    "Otimizando"
                } else {
                    "Adicionar foto de perfil"
                }
                Text(
                    text = text,
                    style = MaterialTheme.typography.bodyLarge
                )
                Spacer(Modifier.height(8.dp))
                BasicTextFieldCustom(
                    value = uiState.firstName,
                    isError = uiState.firstNameError,
                    onValueChange = {
                        onAction(SignUpUiAction.onNameChange(it))
                    },
                    label = "Primeiro Nome",
                    imeAction = ImeAction.Next
                )

                Spacer(Modifier.height(22.dp))
                BasicTextFieldCustom(
                    value = uiState.lastName,
                    isError = uiState.lastNameError,
                    onValueChange = {
                        onAction(SignUpUiAction.onLastNameChange(it))
                    },
                    label = "Ultimo Nome",
                    imeAction = ImeAction.Next
                )

                Spacer(Modifier.height(22.dp))
                BasicTextFieldCustom(
                    value = uiState.email,
                    isError = uiState.emailError,
                    onValueChange = {
                        onAction(SignUpUiAction.onEmailChange(it))
                    },
                    label = "Email",
                    imeAction = ImeAction.Next
                )

                Spacer(Modifier.height(22.dp))


                BasicTextFieldCustom(
                    value = uiState.password,
                    isError = uiState.passwordError,
                    onValueChange = {
                        onAction(SignUpUiAction.onPasswordChange(it))
                        onAction(SignUpUiAction.onExtraTextChange)
                    },
                    keyboardType = KeyboardType.Password,
                    label = "Senha",
                    extraText = uiState.extraText,
                    imeAction = ImeAction.Next
                )
                Spacer(Modifier.height(22.dp))
                BasicTextFieldCustom(
                    value = uiState.confirmPassword,
                    isError = uiState.confirmPasswordError,
                    onValueChange = {
                        onAction(SignUpUiAction.onConfirmPasswordChange(it))
                        onAction(SignUpUiAction.onExtraTextChange)
                    },
                    extraText = uiState.extraText,
                    keyboardType = KeyboardType.Password,
                    label = "Confirmação de senha",
                    imeAction = ImeAction.Done
                )
                Spacer(Modifier.height(36.dp))
                CommomButton(title = "Cadastrar", onClick = {
                    onAction(SignUpUiAction.onSubmit)
                }, modifier = Modifier.height(64.dp))
                Spacer(Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(text = "Possui Conta")
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Faça login aqui",
                        color = Turquoise80,
                        modifier = Modifier.bottomBorder(strokeWidth = 1.dp, color = Turquoise80)
                    )
                }

                val sheetState = rememberModalBottomSheetState()
                val coroutine = rememberCoroutineScope()
                if (uiState.bottomSheet) {
                    ProfilePictureOptionsModalBottomSheet(
                        sheetState = sheetState,
                        onDismissRequest = { onAction(SignUpUiAction.onBottomSheetChange(false)) },
                        onPictureSelected = {
                            onAction(SignUpUiAction.onProfilePictureSelected(it))
                            coroutine.launch(Dispatchers.IO) {
                                sheetState.hide()
                            }.invokeOnCompletion {
                                if (!sheetState.isVisible) {
                                    onAction(SignUpUiAction.onBottomSheetChange(false))
                                }
                            }
                        }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun SignUpPreview() {
    SignUpContent(
        uiState = SignUpState(),
        onAction = {}

    )
}