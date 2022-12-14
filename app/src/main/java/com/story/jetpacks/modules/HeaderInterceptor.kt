package com.story.jetpacks.modules

import com.story.prefs.UserStorage
import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor constructor(
    val userStorage: UserStorage
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return synchronized(this) {
            val originalRequest = chain.request()
            val requestBuilder =
                if (originalRequest.header(HEADER_SKIP_ALL).equals(HEADER_SKIP_ALL)) {
                    originalRequest.newBuilder()
                } else {
                    originalRequest.newBuilder().apply {
                        header("Content-Type", "application/json")
                    }
                }
            chain.proceed(requestBuilder.build())
        }
    }

    companion object {
        const val HEADER_SKIP_ALL = "skip"
    }
}