package com.story.jetpacks.modules

import com.ezetap.network.source.NetworkSource
import com.story.jetpacks.source.network.face.RemoteSource
import com.story.jetpacks.source.network.impl.RemoteSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class RepoBinder {

    @Binds
    abstract fun provideItemNetworkRemote(impl: RemoteSourceImpl): NetworkSource
}