package com.story.variables.enums

/**
 * [ResponseType] used to tell kind of Api Response
 *
 
 */
interface ResponseType {
    companion object {
        const val RESPONSE_SUCCESS = 1
        const val RESPONSE_REFRESH = 3
        const val RESPONSE_ERROR = 2
    }
}