package com.story.jetpacks.retorfit

import com.story.BuildConfig
import com.story.jetpacks.entities.*
import com.story.prefs.TempStorage
import com.story.prefs.UserStorage
import java.net.URLEncoder
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
     * Number verification URL using [LoginModel] used by [LoginRemoteSource.apiLogin]
     */
    fun fetchItems(): String {
        return BuildConfig.API_URL + "api"
    }

    companion object{
        const val GET_ITEMS = ""
    }
}
