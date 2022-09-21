package com.ezetap.network.source

interface NetworkSource {
    fun fetchCustomUrl(url : String, listener: ResponseListener)
    fun fetchImage(url : String, listener: ResponseListener)

    fun cancelApiCalls()
}

interface ResponseListener{
    fun onApiSuccess(any: Any?)

    fun onErrorMessage(statusCode: Int, errorMessage: String?)

}