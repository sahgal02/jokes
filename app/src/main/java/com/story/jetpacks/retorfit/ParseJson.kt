package com.story.jetpacks.retorfit

import com.story.R
import com.story.prefs.UserStorage
import com.story.utilities.MyApplication
import com.story.variables.abstracts.OnEventTriggerListener
import org.json.JSONObject
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Parsing all json responses
 */
@Singleton
class ParseJson @Inject constructor(val userStorage: UserStorage) {
    fun dataCheck(jsonObject: JSONObject?, key: String): Boolean {
        return jsonObject != null && jsonObject.has(key) && !jsonObject.isNull(key)
    }

    private fun parseErrorResponse(listener: OnEventTriggerListener) {
        listener.onErrorMessage(
            1,
            MyApplication.instance.applicationContext.getString(R.string.string_message_check_connection)
        )
    }

    /**
     * Check api call status is successfully running or not
     */
    fun isApiRunSuccessful(response: Response<Any?>): Boolean {
        return response.body() != null && response.isSuccessful && isApiRunSuccessful(response.code())
    }

    /**
     * Check api call status code is under success or failure
     */
    fun isApiRunSuccessful(responseCode: Int): Boolean {
        return responseCode in 200..299
    }

}