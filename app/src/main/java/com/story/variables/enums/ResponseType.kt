package com.story.variables.enums

/**
 * [ResponseType] used to tell kind of Api Response
 *
 
 */
sealed class ResourceState<T> {
    data class Success<T>(val body: T, val code: Int = 200) : ResourceState<T>()

    data class Failure<T>(
        val exception: Throwable,
        val code: Int = 500,
        val requestTag: String = "",
        val body: T? = null,
        val statusCode: Int = -1
    ) : ResourceState<T>()

    data class TokenFailure<T>(val code: Int) : ResourceState<T>()

    data class SessionExpired<T>(
        val exception: Throwable, val code: Int
    ) : ResourceState<T>()

    class Cancelled<T> : ResourceState<T>()
}