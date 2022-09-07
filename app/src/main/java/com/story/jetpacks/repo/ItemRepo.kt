package com.story.jetpacks.repo

import com.story.jetpacks.entities.ItemModel
import com.story.jetpacks.source.data.face.ItemDataSource
import com.story.jetpacks.source.network.face.ItemRemoteSource
import com.story.variables.abstracts.OnEventTriggerListener
import com.story.variables.enums.ItemOrderType
import javax.inject.Inject

class ItemRepo @Inject constructor(
    val dataSource: ItemDataSource,
    val remoteSource: ItemRemoteSource
) {
    val items = dataSource.getItems()

    fun select() {
        dataSource.select()
    }

    /**
     * Api call to fetch items
     */
    fun fetchItems(listener: OnEventTriggerListener) {
        remoteSource.apiFetchItems(object : OnEventTriggerListener(){
            override fun onApiSuccess(any: Any?) {
                super.onApiSuccess(any)
                dataSource.insert(any as ItemModel)
                listener.onApiSuccess(any)
            }

            override fun onErrorMessage(statusCode: Int, errorMessage: String?) {
                super.onErrorMessage(statusCode, errorMessage)
                listener.onErrorMessage(statusCode, errorMessage)
            }
        })
    }

    fun clearItems() {
        dataSource.clearAll()
    }


    fun closeEverything() {
        remoteSource.cancelApiCalls()
    }
}