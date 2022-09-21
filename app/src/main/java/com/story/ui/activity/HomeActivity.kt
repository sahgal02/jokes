package com.story.ui.activity

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.commit
import com.story.R
import com.story.base.BaseActivity
import com.story.databinding.ActivityMainBinding
import com.story.jetpacks.entities.DataModel
import com.story.jetpacks.extensions.loadImage
import com.story.ui.frag.UiFrag
import com.story.utilities.Utils
import com.story.variables.interfaces.IntentKeys
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding
    private val model : DataModel by lazy {
        intent.getParcelableExtra(IntentKeys.INTENT_FOR_MODEL)!!
    }
    private val frag = UiFrag()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        setToolbar(model.heading)
        initializeView()
        initializeFragsView()
    }

    override fun initializeView() {
        super.initializeView()
        binding.apply {
            logo.loadImage(model.logo, R.drawable.placeholder_rectangle)
            collapsingAppBar.title=model.heading
        }
    }

    override fun setToolbar(title: String?) {
        super.setToolbar(title)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        Utils.doStatusColorWhite(window)
    }

    override fun initializeFragsView() {
        super.initializeFragsView()
        frag.arguments = Bundle().also {
            it.putParcelable(IntentKeys.INTENT_FOR_MODEL, model)
        }
        supportFragmentManager.commit {
            replace(R.id.frag, frag)
            setReorderingAllowed(true)
            addToBackStack(null)
        }
    }

}