package com.story.jetpacks.retorfit;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Url;


public interface RetrofitInterface {

    @POST
    Call<Object> commonPost(
            @Header("Authorization") String auth, @Url String url,
            @Body HashMap<String, Object> map
    );


    /**
     * Common API Calls
     * <p>
     * Common [GET] call of [retrofit2.Retrofit]
     *
     * @param url Link to load information
     * @return Object is a kind of Generic implementation to return anything which will handle manually
     */
    @GET
    Call<Object> commonGet(@Url String url);
}
