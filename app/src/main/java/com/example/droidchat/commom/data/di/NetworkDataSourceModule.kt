package com.example.droidchat.commom.data.di

import com.example.droidchat.commom.data.datasource.networkdatasouce.NetworkDataSource
import com.example.droidchat.commom.data.datasource.networkdatasouce.NetworkDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface NetworkDataSourceModule {

    @Binds
    @Singleton
    fun bindNetworkDataSource(networkDataSourceImpl: NetworkDataSourceImpl): NetworkDataSource
}