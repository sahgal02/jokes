package com.story.jetpacks.modules

import com.story.jetpacks.source.data.face.ItemDataSource
import com.story.jetpacks.source.data.impls.ItemDataSourceImpl
import com.story.jetpacks.source.network.face.ItemRemoteSource
import com.story.jetpacks.source.network.impl.ItemRemoteSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class RepoBinder {

    @Binds
    abstract fun provideItemNetworkRemote(impl: ItemRemoteSourceImpl): ItemRemoteSource

    @Binds
    abstract fun provideItemDataRemote(impl: ItemDataSourceImpl): ItemDataSource
}