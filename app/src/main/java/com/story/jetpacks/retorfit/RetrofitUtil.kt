package com.story.jetpacks.retorfit

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.story.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type
import java.util.concurrent.TimeUnit

/**
 * Retrofit Util for Retrofit Initialization and Instance signup
 *
 */
class RetrofitUtil {
    val gson: Gson = GsonBuilder()
        .setLenient()
        .create()

    /**
     * Method used to create type token which provide Jamun Volley a format to create or parse json
     *
     * @param typeToken You need to type VolleyGSON.get().getTypeToken(new TypeToken<Model.Class or List></Model.Class><Model>>(){});
     * @return object
    </Model> */
    fun getTypeToken(typeToken: TypeToken<*>): Type {
        return typeToken.type
    }

    /**
     * Method used to create JSON From Model or Collection Variable
     *
     * @param object Your Collection or model Object
     * @param type   You need to type VolleyGSON.get().getTypeToken(new TypeToken<Model.Class or List></Model.Class><Model>>(){});
     * @return String of json
    </Model> */
    fun toJson(`object`: Any?, type: Type?): String {
        return gson.toJson(`object`, type)
    }

    /**
     * Method used to create JSON From Model or Collection Variable
     *
     * @param object Your Collection or model Object
     * @param type   You need to type VolleyGSON.get().getTypeToken(new TypeToken<Model.Class or List></Model.Class><Model>>(){});
     * @return String of json
    </Model> */
    fun <T> toJson(data: String, classOfT: Class<T>): T {
        return gson.fromJson(data, classOfT)
    }

    /**
     * Method used to parse your data into Your Model Object
     *
     * @param type You need to type VolleyGSON.get().getTypeToken(new TypeToken<Model.Class or List></Model.Class><Model>>(){});
     * @param data String of your JSON response
     * @return Return you Object of your Model Class
    </Model> */
    fun fromJson(type: Type?, data: String) {
        return gson.fromJson(data, type)
    }

    /**
     * Method used to parse your data into Your Model Object
     *
     * @param type You need to type VolleyGSON.get().getTypeToken(new TypeToken<Model.Class or List></Model.Class><Model>>(){});
     * @param data String of your JSON response
     * @return Return you Object of your Model Class
    </Model> */
    fun <T> fromJson(data: String, classOfT: Class<T>): T {
        return gson.fromJson(data, classOfT)
    }

    /**
     * Method used to parse your data into Your Model Object
     *
     * @param type You need to type VolleyGSON.get().getTypeToken(new TypeToken<Model.Class or List></Model.Class><Model>>(){});
     * @param data String of your JSON response
     * @return Return you Object of your Model Class
    </Model> */
    fun toMap(data: String): HashMap<String, Any> {
        return gson.fromJson(data, HashMap::class.java) as HashMap<String, Any>
    }

    fun getResponse(response: Response<Any?>): JSONObject {
        return JSONObject(gson.toJson(response.body()))
    }

    fun getResponseArray(response: Response<Any?>): JSONArray {
        return JSONArray(gson.toJson(response.body()))
    }

    companion object {
        /**
         * Get singleton implementation of Retrofit Util to access its Data Members [RetrofitInterface] and [Gson]
         * @update : Implementing [val] datatype implementation which means Assign once
         */
        val instance: RetrofitUtil = RetrofitUtil()
    }
}