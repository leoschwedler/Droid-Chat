package com.example.droidchat.commom.data.repository

import com.example.droidchat.commom.data.datasource.networkdatasouce.NetworkDataSource
import com.example.droidchat.commom.domain.model.CreateAccount
import com.example.droidchat.commom.domain.model.LoginAccount
import com.example.droidchat.commom.domain.model.toSignInRequest
import com.example.droidchat.commom.domain.model.toSignUpRequest
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val networkDataSource: NetworkDataSource) :
    AuthRepository {
    override suspend fun signUp(createAccount: CreateAccount) {
        val request = createAccount.toSignUpRequest()
        networkDataSource.signUp(request)
    }

    override suspend fun signIn(loginAccount: LoginAccount) {
        val request = loginAccount.toSignInRequest()
        networkDataSource.signIn(request)
    }
}