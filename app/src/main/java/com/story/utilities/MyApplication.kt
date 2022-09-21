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
import javax.inject.Inject


/**
 * [android.app.Application] class Initialize Important SDK's like [FirebaseApp] and Maintain User Session info and User Profile
 */
@HiltAndroidApp
class MyApplication : MultiDexApplication(){

    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    @Inject
    lateinit var userStorage: UserStorage

    @Inject
    lateinit var tempStorage: TempStorage

    /**
     * AppCompatDelegate property enable Vector Resources
     */
    init {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }


    companion object {

        /**
         * [MyApplication] Class [instance] to access objects and [getApplicationContext]
         */
        @get:Synchronized
        @Volatile
        lateinit var instance: MyApplication
            private set

        var accessToken: String? = null
    }
}