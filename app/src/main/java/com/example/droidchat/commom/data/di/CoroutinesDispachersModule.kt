package com.example.droidchat.commom.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoroutinesDispachersModule {

    @IoDispatcher
    @Provides
    @Singleton
    fun provideDispachersIo(): CoroutineDispatcher{
        return Dispatchers.IO
    }


    @DefaultDispatcher
    @Provides
    @Singleton
    fun provideDispachersDefault(): CoroutineDispatcher{
        return Dispatchers.IO
    }
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class IoDispatcher

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DefaultDispatcher