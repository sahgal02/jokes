package com.story.jetpacks.source.network.face

import com.remedoapp.patient.jetpacks.base.BaseRemotePattern
import com.story.variables.abstracts.OnEventTriggerListener

interface RemoteSource : BaseRemotePattern {

    fun apiFetchPlanets(listener: OnEventTriggerListener)

    fun apiFetchVehicles(listener: OnEventTriggerListener)

    fun apiFetchToken(listener: OnEventTriggerListener)

    fun apiFindFalconne(payloadModel : HashMap<String, String>, listener: OnEventTriggerListener)
}
