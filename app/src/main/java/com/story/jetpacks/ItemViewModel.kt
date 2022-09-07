package com.story.jetpacks

import androidx.lifecycle.ViewModel
import com.story.jetpacks.repo.ItemRepo
import com.story.variables.abstracts.OnEventTriggerListener
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ItemViewModel @Inject constructor(val repo: ItemRepo) : ViewModel() {

    val items = repo.items

    fun fetchItems(listener: OnEventTriggerListener) {
        repo.fetchItems(listener)
    }

    fun select() {
        repo.select()
    }

    fun clear() {
        repo.clearItems()
    }

    override fun onCleared() {
        super.onCleared()
        cancelApi()
    }

    fun cancelApi() {
        repo.closeEverything()
    }
}