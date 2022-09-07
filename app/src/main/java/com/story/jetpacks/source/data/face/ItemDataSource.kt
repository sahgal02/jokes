package com.story.jetpacks.source.data.face

import androidx.lifecycle.MutableLiveData
import com.remedoapp.patient.jetpacks.base.BaseDataPattern
import com.story.jetpacks.entities.ItemModel
import com.story.variables.enums.ItemOrderType

interface ItemDataSource : BaseDataPattern {

    fun getItems() : MutableLiveData<List<ItemModel>>

    fun select()

    fun insert(model: ItemModel)

    fun clearAll()


}