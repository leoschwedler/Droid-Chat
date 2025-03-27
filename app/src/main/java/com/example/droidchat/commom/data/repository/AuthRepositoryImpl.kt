package com.example.droidchat.commom.data.repository

import com.example.droidchat.commom.data.datasource.networkdatasouce.NetworkDataSource
import com.example.droidchat.commom.data.di.IoDispatcher
import com.example.droidchat.commom.domain.model.CreateAccount
import com.example.droidchat.commom.domain.model.LoginAccount
import com.example.droidchat.commom.domain.model.toSignInRequest
import com.example.droidchat.commom.domain.model.toSignUpRequest
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val networkDataSource: NetworkDataSource,
    @IoDispatcher private val ioDispacher: CoroutineDispatcher
) :
    AuthRepository {
    override suspend fun signUp(createAccount: CreateAccount): Result<Unit> {
        return withContext(ioDispacher){
            runCatching {
                val request = createAccount.toSignUpRequest()
                networkDataSource.signUp(request)
            }
        }
    }

    override suspend fun signIn(loginAccount: LoginAccount) {
        val request = loginAccount.toSignInRequest()
        networkDataSource.signIn(request)
    }
}