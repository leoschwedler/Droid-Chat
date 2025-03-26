package com.example.droidchat.commom.data.datasource.networkdatasouce

import com.example.droidchat.commom.data.network.model.SignInRequest
import com.example.droidchat.commom.data.network.model.SignUpRequest
import com.example.droidchat.commom.data.network.model.TokenResponse
import com.example.droidchat.commom.exception.handleNetworkException
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import javax.inject.Inject

class NetworkDataSourceImpl @Inject constructor(private val httpClient: HttpClient) :
    NetworkDataSource {
    override suspend fun signUp(signUpRequest: SignUpRequest) {
        handleNetworkException {
            httpClient.post("signup") {
                setBody(signUpRequest)
            }.body<Unit>()
        }
    }

    override suspend fun signIn(signInRequest: SignInRequest): TokenResponse {
        return handleNetworkException {
            httpClient.post("signin") {
                setBody(signInRequest)
            }.body()
        }
    }
}