package com.story.jetpacks.source.data.impls

import androidx.lifecycle.MutableLiveData
import androidx.room.Transaction
import com.story.jetpacks.dao.ItemDao
import com.story.jetpacks.entities.ItemModel
import com.story.jetpacks.source.data.face.ItemDataSource
import com.story.utilities.Utils
import com.story.variables.enums.ItemOrderType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * [ItemDataSourceImpl] manage all Room transaction of [AddressModel] using [CoroutineScope]
 */
class ItemDataSourceImpl @Inject constructor(
    private val dao: ItemDao
) : ItemDataSource {
    private val dispatcher = CoroutineScope(Dispatchers.IO)
    private val items: MutableLiveData<List<ItemModel>> = MutableLiveData()

    override fun getItems() : MutableLiveData<List<ItemModel>>  = items

    override fun insert(model: ItemModel) {
        dispatcher.launch {
            dao.insert(model)
        }
    }

    override fun clearAll() {
        dispatcher.launch {
            dao.clearAll()
        }
    }

    @Transaction
    override fun select() {
        dispatcher.launch {
            items.postValue(dao.select())
        }
    }
}