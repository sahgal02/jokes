package com.story.jetpacks.retorfit

import com.story.BuildConfig
import com.story.jetpacks.entities.*
import com.story.jetpacks.source.network.impl.RemoteSourceImpl
import com.story.prefs.TempStorage
import com.story.prefs.UserStorage
import javax.inject.Inject
import javax.inject.Singleton

/**
 * [Urls] used for all kind of URL creation and listing in [RemoteSource]
 *
 */
@Singleton
class Urls @Inject constructor(
    val tempStorage: TempStorage,
    val userStorage: UserStorage
) {

    /**
     * Find planets from URL using [PlanetModel] used by [RemoteSourceImpl.apiFetchPlanets]
     */
    fun fetchCustom(): String {
        return BuildConfig.API_URL + "mobileapps/android_assignment.json"
    }

}
