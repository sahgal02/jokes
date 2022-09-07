package com.story.variables.abstracts

import com.story.variables.enums.ActionType
import com.story.variables.interfaces.BluePrint

open class OnEventTriggerListener : BluePrint.OnEventTriggers {
    /**
     * Used only to get api response if api run successfull
     */
    override fun onApiSuccess(any: Any?) {

    }

    /**
     * Used only to get api response if api run with error
     */
    override fun onErrorMessage(statusCode: Int, errorMessage: String?) {

    }

    override fun getEvent(any: Any) {

    }

    override fun getEvent(any: Any, actionType: ActionType, variable: Any, view: Any) {
    }

    override fun getEvent(any: Any, actionType: ActionType, variable: Any) {
    }

    override fun getEvent(any: Any, actionType: ActionType) {

    }
}