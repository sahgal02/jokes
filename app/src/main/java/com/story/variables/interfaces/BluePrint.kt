package com.story.variables.interfaces

import android.content.Intent
import androidx.appcompat.widget.Toolbar
import com.story.variables.enums.ActionType

interface BluePrint {
    interface OnEventTriggers {
        fun onApiSuccess(any: Any?)

        fun onErrorMessage(statusCode: Int, errorMessage: String?)

        fun getEvent(any: Any)

        fun getEvent(any: Any, actionType: ActionType, variable: Any)

        fun getEvent(any: Any, actionType: ActionType, variable: Any, view: Any)

        fun getEvent(any: Any, actionType: ActionType)
    }


    interface OfActivity {
        fun setToolbar(title: String? = null)

        fun setToolbar(toolbar: Toolbar, title: String? = null, statusColorWhite: Boolean? = true)

        fun setToolbar(toolbar: Toolbar, title: Int)

        fun connectionUpdate(hasConnection: Boolean)
    }


    interface OfView {
        fun initializeView()

        fun initializeListeners()

        fun initializeData()

        fun initializePicker()

        fun initializeViewModel()

        fun initializeTabView()

        fun closeEverything()

        fun onResult(requestCode: Int, resultCode: Int, data: Intent?)

        fun onPermissionResult(requestCode: String, isGranted: Boolean)
    }

    interface OfRecycler {
        fun initializeRecyclerView()

        fun initializeEmptyView(isEmpty: Boolean)
    }

    interface OfFrag {
        fun initializeFragsView()
    }
}