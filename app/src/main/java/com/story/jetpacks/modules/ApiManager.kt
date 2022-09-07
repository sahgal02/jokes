package com.story.jetpacks.modules

import com.story.BuildConfig
import com.story.jetpacks.retorfit.RetrofitCalls
import com.story.jetpacks.retorfit.RetrofitInterface
import com.story.jetpacks.retorfit.RetrofitUtil
import retrofit2.Retrofit

class ApiManager constructor(
    private val retrofit: Retrofit
) {
    val retrofitApis by lazy { retrofit.updateBaseUrl(BuildConfig.API_URL).createApi<RetrofitCalls>() }

    val retrofitOldApis by lazy { retrofit.updateBaseUrl(BuildConfig.API_URL).createApi<RetrofitInterface>() }
}


inline fun <reified T> Retrofit.createApi(): T = this.create(T::class.java)
fun Retrofit.updateBaseUrl(baseUrl: String): Retrofit = this.newBuilder().baseUrl(baseUrl).build()