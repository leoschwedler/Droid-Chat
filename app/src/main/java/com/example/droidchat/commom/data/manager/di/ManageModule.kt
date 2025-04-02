package com.example.droidchat.commom.data.manager.di

import com.example.droidchat.commom.data.manager.SelfUserManager
import com.example.droidchat.commom.data.manager.SelfUserManagerImpl
import com.example.droidchat.commom.data.manager.TokenManager
import com.example.droidchat.commom.data.manager.TokenManagerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface ManageModule {

    @Binds
    @Singleton
    fun bindTokenManager(tokenManagerImpl: TokenManagerImpl): TokenManager


    @Binds
    @Singleton
    fun bindSelfUserManager(selfUserManagerImpl: SelfUserManagerImpl): SelfUserManager
}