package com.story.utilities

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import android.view.View
import android.view.Window
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.story.R
import com.story.utilities.Utils.helperDialog
import com.story.utilities.Utils.setVisibility
import javax.inject.Singleton
import kotlin.math.roundToInt

/**
 * [Log] function for ease creating console using common key [key]
 *
 * @param key Unique identity key, Please edit auto key according to you...
 * @param message any object for console
 *
 */
fun remedoLogs(message: Any?, key: String? = "jamun") {
    Log.d(key, message.toString())
}

/**
 *  Most of Utils function for ease like [setVisibility], [helperDialog].
 */
@Singleton
object Utils {

    /**
     * [Toast] function for ease using [message] and [context] of Page
     */
    fun toast(context: Context, message: String?) {
        message?.let {
            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
        }
    }

    /**
     * [Log] function for ease creating console using common key [key]
     *
     * @param key Unique identity key, Please edit auto key according to you...
     * @param message any object for console
     *
     */
    fun log(message: Any?, key: String? = "Jamun") {
        Log.d(key, message.toString())
    }

    /**
     * Set visibility of a View
     *
     * @param view Generic implementation for any kind of [View]
     * @param isVisible true for Visible, false for GONE
     */
    fun setVisibility(view: View, isVisible: Boolean? = true) {
        view.visibility = if (isVisible!!) View.VISIBLE else View.GONE
    }

    /**
     * Helper function creating or managing default values for [AlertDialog]
     *
     * @param view Root [View] example R.layout.dialog_video_feedback
     * @param context [Context] of View
     */
    fun helperDialog(view: View, context: Context): AlertDialog {
        val alertDialog = AlertDialog.Builder(context).create()
        val window = alertDialog.window
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        alertDialog.setCanceledOnTouchOutside(false)
        alertDialog.setCancelable(true)
        alertDialog.setView(view)
        alertDialog.show()
        return alertDialog
    }


    /**
     * Function used for Status bar color black
     */
    fun doStatusColorBlack(window: Window) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            window.statusBarColor = ContextCompat.getColor(
                MyApplication.instance.applicationContext,
                R.color.colorBlack
            )
        }
    }



    /**
     * Function used for Status bar color white
     */
    fun doStatusColorWhite(window: Window) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            window.statusBarColor = Color.WHITE
        }
    }

    /**
     * Check network connectivity and information regarding network enable
     */
    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (CheckOs.checkBuildMarshmallow()) {
            val nw = connectivityManager.activeNetwork ?: return false
            val actNw = connectivityManager.getNetworkCapabilities(nw) ?: return false
            return when {
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                else -> false
            }
        } else {
            val nwInfo = connectivityManager.activeNetworkInfo ?: return false
            return nwInfo.isConnected
        }
    }

}
