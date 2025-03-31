package com.example.droidchat.commom.util.di

import com.example.droidchat.commom.util.image.ImageCompressor
import com.example.droidchat.commom.util.image.ImageCompressorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
interface ImageCompressorModule {

    @Binds
    fun bindImageCompressor(imageCompressorImpl: ImageCompressorImpl): ImageCompressor
}