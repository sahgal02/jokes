package com.story.ui.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import androidx.activity.viewModels
import com.story.R
import com.story.base.BaseActivity
import com.story.databinding.ActivityLauncherBinding
import com.story.jetpacks.ItemViewModel
import com.story.jetpacks.entities.DataModel
import com.story.jetpacks.source.network.impl.RemoteSourceImpl
import com.story.utilities.MyApplication
import com.story.utilities.Utils
import com.story.variables.abstracts.OnEventTriggerListener
import com.story.variables.enums.ResourceState
import com.story.variables.interfaces.IntentKeys

class LauncherActivity : BaseActivity() {
    private lateinit var binding: ActivityLauncherBinding
    private val viewModel by viewModels<ItemViewModel>()
    private var handler: Handler = Handler(Looper.getMainLooper())
    private var dataModel:DataModel?=null
    private val runnable: Runnable = object : Runnable {
        override fun run() {
            val progress = binding.idProgressBar.progress
            when {
                progress > 98 -> {
                    homeStart()
                    return
                }
                progress >= 70 -> {
                    binding.idTextProgress.text = getString(R.string.string_label_setting_up)
                }
                progress >= 50 -> {
                    binding.idTextProgress.text = getString(R.string.string_label_config)
                }
            }
            binding.idProgressBar.progress = progress + 4
            handler.postDelayed(this, 200)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLauncherBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        setUpObserver()
        initializeView()
        handler.postDelayed(runnable, 200)
        initializeViewModel()
    }
    private  fun setUpObserver(){
       viewModel.fetchedResponse.observe(this){
           when(it){
               is ResourceState.Success->{
                   dataModel = it.body
                   setUploadStatus(false)
               }
               is ResourceState.Failure->{
                   setUploadStatus(false)
                   homeStart()
               }
               else ->{
               }
           }
       }
    }

    /**
     * Api call to get token : [RemoteSourceImpl.apiFetchToken]
     */
    override fun initializeViewModel() {
        super.initializeViewModel()
        if (!isUploading() && hasConnection()) {
            setUploadStatus(true)
            viewModel.fetchCustomUrl()
        } else {
            homeStart()
        }
    }

    private fun homeStart() {
        closeEverything()
        if (dataModel==null)
            ConnectionActivity.launch(this)
        else {
            startActivity(Intent(this, HomeActivity::class.java)
                .putExtra(IntentKeys.INTENT_FOR_MODEL, dataModel))
            finish()
        }
    }

    override fun onResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onResult(requestCode, resultCode, data)
        if (requestCode == ConnectionActivity.REQUEST_CODE && resultCode == RESULT_OK) {
            binding.idTextProgress.text = getString(R.string.string_label_initializing)
            binding.idProgressBar.progress = 0
            handler.postDelayed(runnable, 200)
            initializeViewModel()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        closeEverything()
    }

    override fun closeEverything() {
        handler.removeCallbacks(runnable)
    }

}
