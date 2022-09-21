package com.story.jetpacks

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ezetap.network.source.ResponseListener
import com.story.jetpacks.entities.DataModel
import com.story.jetpacks.repo.ItemRepo
import com.story.jetpacks.source.network.impl.RemoteSourceImpl
import com.story.variables.enums.ResourceState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ItemViewModel @Inject constructor(val repo: ItemRepo) : ViewModel() {

    val fetchedResponse = MutableLiveData<ResourceState<DataModel>>()

    /**
     * Api call to fetch token : [RemoteSourceImpl.apiFetchPlanets]
     */
    fun fetchCustomUrl() {
        repo.fetchCustomUrl(object : ResponseListener{
            override fun onApiSuccess(any: Any?) {
                fetchedResponse.postValue(ResourceState.Success(any as DataModel))
            }

            override fun onErrorMessage(statusCode: Int, errorMessage: String?) {
                fetchedResponse.postValue(ResourceState.Failure(
                    exception=Exception(errorMessage),
                    code=statusCode
                ))
            }
        })
    }
    /**
     * Api call to fetch token : [RemoteSourceImpl.apiFetchPlanets]
     */
    fun fetchImage(url:String, listener: ResponseListener) {
        repo.fetchImage(url, listener)
    }
    override fun onCleared() {
        super.onCleared()
        cancelApi()
    }

    fun cancelApi() {
        repo.closeEverything()
    }
}