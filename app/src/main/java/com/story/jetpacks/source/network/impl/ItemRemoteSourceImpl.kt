package com.story.jetpacks.source.network.impl

import com.story.jetpacks.entities.ItemModel
import com.story.jetpacks.retorfit.ParseJson
import com.story.jetpacks.retorfit.RetrofitCalls
import com.story.jetpacks.retorfit.Urls
import com.story.jetpacks.source.network.face.ItemRemoteSource
import com.story.variables.abstracts.OnEventTriggerListener
import com.story.variables.enums.ResponseType
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class ItemRemoteSourceImpl @Inject constructor(
    val parseJson: ParseJson,
    private val urls: Urls,
    private val retrofitCalls: RetrofitCalls
) : ItemRemoteSource {
    private var apiCall: Call<Any>? = null

    override fun apiFetchItems(listener: OnEventTriggerListener) {
        apiCall = retrofitCalls.commonGet(
            urls.fetchItems()
        )
        apiCall?.enqueue(object : Callback<Any?> {
            override fun onResponse(call: Call<Any?>, response: Response<Any?>) {
                if (parseJson.isApiRunSuccessful(response)) {
                    val joke = response.body() as String
                    listener.onApiSuccess(ItemModel(joke = joke))
                    return
                }
                listener.onErrorMessage(ResponseType.RESPONSE_ERROR, "No more jokes available.")
            }

            override fun onFailure(call: Call<Any?>, t: Throwable) {
                if (!call.isCanceled)
                    listener.onErrorMessage(ResponseType.RESPONSE_ERROR, t.localizedMessage)
            }
        })
    }

    override fun cancelApiCalls() {
        apiCall?.cancel()
    }
}