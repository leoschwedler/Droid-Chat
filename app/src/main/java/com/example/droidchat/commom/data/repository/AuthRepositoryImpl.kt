package com.example.droidchat.commom.data.repository

import com.example.droidchat.commom.data.datasource.networkdatasouce.NetworkDataSource
import com.example.droidchat.commom.data.di.IoDispatcher
import com.example.droidchat.commom.data.manager.SelfUserManager
import com.example.droidchat.commom.data.manager.TokenManager
import com.example.droidchat.commom.data.network.model.SignInRequest
import com.example.droidchat.commom.data.network.model.toImageDomain
import com.example.droidchat.commom.domain.model.CreateAccount
import com.example.droidchat.commom.domain.model.ImageDomain
import com.example.droidchat.commom.domain.model.LoginAccount
import com.example.droidchat.commom.domain.model.toSignUpRequest
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val networkDataSource: NetworkDataSource,
    private val tokenManager: TokenManager,
    private val selfUserManager: SelfUserManager,
    @IoDispatcher private val ioDispacher: CoroutineDispatcher
) :
    AuthRepository {
    override suspend fun signUp(createAccount: CreateAccount): Result<Unit> {
        return withContext(ioDispacher) {
            runCatching {
                val response = createAccount.toSignUpRequest()
                networkDataSource.signUp(response)
            }
        }
    }

    override suspend fun signIn(loginAccount: LoginAccount): Result<Unit> {
        return withContext(ioDispacher) {
            runCatching {
                val tokenResponse = networkDataSource.signIn(
                    signInRequest = SignInRequest(
                        username = loginAccount.username,
                        password = loginAccount.password
                    )
                )
                tokenManager.saveAccessToken(tokenResponse.token)
            }
        }
    }

    override suspend fun profilePicture(filePath: String): Result<ImageDomain> {
        return withContext(ioDispacher) {
            runCatching {
                val response = networkDataSource.profilePicture(filePath)
                response.toImageDomain()
            }
        }
    }

    override suspend fun authenticate(token: String): Result<Unit> {
        return withContext(ioDispacher) {
            runCatching {
                val authenticateResponse = networkDataSource.authenticate(token)
                selfUserManager.saveSelfUserData(
                    firstName = authenticateResponse.firstName,
                    lastName = authenticateResponse.lastName,
                    username = authenticateResponse.username,
                    profilePictureUrl = authenticateResponse.profilePictureUrl
                )
            }
        }
    }

    override suspend fun getAccessToken(): String? {
        return withContext(ioDispacher) {
            tokenManager.accessToken.firstOrNull()
        }
    }

    override suspend fun clearAccessToken() {
        withContext(ioDispacher) {
            tokenManager.clearAccessToken()
        }
    }
}