package com.story.jetpacks.retorfit

import com.story.jetpacks.entities.DataModel
import retrofit2.Call
import retrofit2.http.*

/**
 * Configure and Customizing [RetrofitCalls]
 */
interface RetrofitCalls {

    /**
     * Api call to fetch list of [DataModel]
     *
     * @param url Link to load information : [Urls.fetchCustom]
     * @return List of [DataModel]
     */
    @GET
    fun fetchPlanets(@Url url: String): Call<DataModel>

}
