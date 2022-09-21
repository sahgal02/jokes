package com.story.jetpacks.source.network.impl

import com.ezetap.network.source.NetworkSource
import com.ezetap.network.source.ResponseListener
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.story.jetpacks.entities.DataModel
import com.story.jetpacks.retorfit.RetrofitCalls
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class RemoteSourceImpl @Inject constructor(
    private val retrofitCalls: RetrofitCalls
) : NetworkSource {
    private var apiDataCall: Call<DataModel>? = null
    private var apiCall: Call<Any>? = null

    override fun fetchCustomUrl(url: String, listener: ResponseListener) {
        apiDataCall = retrofitCalls.fetchPlanets(url)
        apiDataCall?.enqueue(object : Callback<DataModel?> {
            override fun onResponse(
                call: Call<DataModel?>,
                response: Response<DataModel?>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    listener.onApiSuccess(response.body() as DataModel)
                    return
                }
                listener.onErrorMessage(response.code(), "Oops, Service issue.")
            }

            override fun onFailure(call: Call<DataModel?>, t: Throwable) {
                if (!call.isCanceled)
                    listener.onErrorMessage(1, t.localizedMessage)
            }
        })
    }

    override fun fetchImage(url: String, listener: ResponseListener) {

    }

    override fun cancelApiCalls() {
        apiCall?.cancel()
        apiDataCall?.cancel()
    }
}