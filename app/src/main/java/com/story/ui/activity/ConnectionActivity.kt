package com.story.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import com.story.base.BaseActivity
import com.story.databinding.ActivityConnectionBinding
import com.story.utilities.Utils
import dagger.hilt.android.AndroidEntryPoint

/**
 * Check connection and show view when no internet available
 * @usedIn [DoctorProfileActivity]
 */
@AndroidEntryPoint
class ConnectionActivity : BaseActivity(), View.OnClickListener {
    private lateinit var binding: ActivityConnectionBinding
    private val handler = Handler(Looper.getMainLooper())
    private val runnable = {
        if (hasConnection()) {
            setResult(RESULT_OK)
        } else {
            binding.idButtonSubmit.isEnabled = true
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConnectionBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        binding.clickListener = this
        Utils.doStatusColorWhite(window)
    }

    override fun onClick(v: View?) {
        binding.idButtonSubmit.isEnabled = false
        handler.postDelayed(runnable, 1500)
    }

    /**
     * Function up-to-date you with Network
     */
    override fun connectionUpdate(hasConnection: Boolean) {
        super.connectionUpdate(hasConnection)
        onClick(null)
    }

    override fun onBackPressed() {
        handler.removeCallbacks(runnable)
        setResult(RESULT_CANCELED)
        super.onBackPressed()
    }

    companion object {
        fun launch(context: BaseActivity) {
            context.launcher(ConnectionActivity.REQUEST_CODE).launch(Intent(context,
                    ConnectionActivity::class.java), )
        }

        const val REQUEST_CODE = 111;
    }
}