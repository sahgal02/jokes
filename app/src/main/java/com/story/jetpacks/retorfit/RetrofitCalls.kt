package com.story.jetpacks.retorfit

import com.google.gson.JsonObject
import com.story.jetpacks.modules.HeaderInterceptor.Companion.HEADER_SKIP_ALL
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*
import java.util.*

/**
 * Configure and Customizing [RetrofitCalls]
 */
interface RetrofitCalls {

   /**
     * Login Via Truecaller
     *
     * @param url Link to load information : [ServerConstants.TRUE_CALLER_LOGIN]
     * @return List of Country
     
     */
    @GET(Urls.GET_ITEMS)
    @Headers("${HEADER_SKIP_ALL}:$HEADER_SKIP_ALL")
    fun fetchItems(@Body jsonObject: JsonObject): Call<Any>

    /** Common API Calls */
    /**
     * Common [GET] call of [retrofit2.Retrofit]
     *
     * @param url Link to load information
     * @return Object is a kind of Generic implementation to return anything which will handle manually
     
     */
    @GET
    fun commonGet( @Url url: String): Call<Any>

    /**
     * Common [DELETE] call of [retrofit2.Retrofit]
     *
     * @param url Link to load information
     * @return Object is a kind of Generic implementation to return anything which will handle manually
     
     */
    @DELETE
    fun commonDelete( @Url url: String): Call<Any>

    /**
     * Common [PUT] call of [retrofit2.Retrofit]
     *
     * @param url Link to upload information
     * @return Object is a kind of Generic implementation to return anything which will handle manually
     
     */
    @PUT
    fun commonPutRequest( @Url url: String, @Body map: HashMap<String, Any?>): Call<Any>

    /**
     * Common [PUT] call of [retrofit2.Retrofit]
     *
     * @param url Link to upload information
     * @return Object is a kind of Generic implementation to return anything which will handle manually
     
     */
    @PUT
    fun commonPut( @Url url: String,
                  @Body jsonObject: JsonObject?): Call<Any>

    /**
     * Common [POST] call of [retrofit2.Retrofit]
     *
     * @param url Link to load information
     * @return Object is a kind of Generic implementation to return anything which will handle manually
     
     */
    @POST
    fun commonPost( @Url url: String, @Body map: HashMap<String, Any?>): Call<Any>

    /**
     * Common [POST] call of [retrofit2.Retrofit]
     *
     * @param url Link to load information
     * @return Object is a kind of Generic implementation to return anything which will handle manually
     
     */
    @POST
    fun commonPost( @Url url: String,
                   @Body jsonObject: JsonObject): Call<Any>

    /**
     * Common [POST] call of [retrofit2.Retrofit] to upload single [MultipartBody]
     *
     * @param url Link to load information
     * @return Object is a kind of Generic implementation to return anything which will handle manually
     
     */
    @Multipart
    @POST
    fun commonUpload(
                     @Url url: String,
                     @PartMap map: HashMap<String, RequestBody>, @Part image: MultipartBody.Part?): Call<Any>

    @Multipart
    @PUT
    fun commonPutUpload( @Url url: String?,  @PartMap map: HashMap<String, RequestBody>): Call<Any>

    @Multipart
    @POST
    fun commonPostUpload(@Url url: String?,  @PartMap map: HashMap<String, RequestBody?>): Call<Any>
}
