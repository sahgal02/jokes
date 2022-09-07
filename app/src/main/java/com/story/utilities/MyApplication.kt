package com.story.utilities

import androidx.appcompat.app.AppCompatDelegate
import androidx.hilt.work.HiltWorkerFactory
import androidx.multidex.MultiDexApplication
import androidx.work.Configuration
import androidx.work.WorkManager
import com.story.prefs.TempStorage
import com.story.prefs.UserStorage
import com.story.utilities.MyApplication.Companion.instance
import dagger.hilt.android.HiltAndroidApp
import java.security.KeyManagementException
import java.security.NoSuchAlgorithmException
import javax.inject.Inject
import javax.net.ssl.SSLContext


/**
 * [android.app.Application] class Initialize Important SDK's like [FirebaseApp] and Maintain User Session info and User Profile
 */
@HiltAndroidApp
class MyApplication : MultiDexApplication(), Configuration.Provider {

    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    @Inject
    lateinit var userStorage: UserStorage

    @Inject
    lateinit var tempStorage: TempStorage

    /**
     * Releted to [ScheduleWorker], we are using [WorkManager]
     */
    override fun getWorkManagerConfiguration() =
        Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()

    /**
     * [AppCompatDelegate] property enable Vector Resources
     */
    init {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        checkAndLoadCredentials()
    }

    /**
     * [checkAndLoadCredentials] used by [UserStorage.createUserIdSession] or [UserStorage.migrateSharedPref]
     * to load information from old implementation and new implementation
     * @update : Logging user to MixPanel
     */
    fun checkAndLoadCredentials() {
    }

    companion object {

        /**
         * [MyApplication] Class [instance] to access objects and [getApplicationContext]
         */
        @get:Synchronized
        @Volatile
        lateinit var instance: MyApplication
            private set

        /**
         * Variables manage by [UserStorage] to access information regarding User and App Session

         */
        var isLoggedIn = false

        /**
         * [userId] is User name actually which is our User unique id,
         * [name] is User actually name made by first, last and middle name
         *
         * Keeping old version in mind check [UserStorage.migrateSharedPref]
         * WHERE we are getting all information from DB which was our last implementation
         *  For new user we are maintaining it from start

         */
        var userId: String? = null

        var name: String? = null

        var accessToken: String? = null
    }
}