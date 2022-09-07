package com.story.jetpacks.source.network.face

import com.remedoapp.patient.jetpacks.base.BaseRemotePattern
import com.story.variables.abstracts.OnEventTriggerListener

interface ItemRemoteSource : BaseRemotePattern {

    fun apiFetchItems(listener: OnEventTriggerListener)

}
