package com.story.jetpacks.repo

import com.ezetap.network.source.NetworkSource
import com.ezetap.network.source.ResponseListener
import com.story.jetpacks.retorfit.Urls
import com.story.jetpacks.source.network.face.RemoteSource
import com.story.jetpacks.source.network.impl.RemoteSourceImpl
import com.story.variables.abstracts.OnEventTriggerListener
import javax.inject.Inject

class ItemRepo @Inject constructor(
    private val url: Urls,
    private val remoteSource: NetworkSource
) {

    /**
     * Api call to fetch token : [RemoteSourceImpl.fetchCustomUrl]
     */
    fun fetchCustomUrl(listener: ResponseListener) {
        remoteSource.fetchCustomUrl(url.fetchCustom(),listener)
    }
    /**
     * Api call to fetch token : [RemoteSourceImpl.fetchImage]
     */
    fun fetchImage(url:String, listener: ResponseListener) {
        remoteSource.fetchImage(url, listener)
    }

    fun closeEverything() {
        remoteSource.cancelApiCalls()
    }
}