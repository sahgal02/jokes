package com.story.ui.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.commit
import com.story.R
import com.story.base.BaseActivity
import com.story.databinding.ActivityMainBinding
import com.story.ui.frag.ListFrag
import com.story.utilities.Utils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding
    private val frag = ListFrag()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        setToolbar("Jokes")
        initializeFragsView()
    }

    override fun setToolbar(title: String?) {
        super.setToolbar(title)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        Utils.doStatusColorBlack(window)
    }

    override fun initializeFragsView() {
        super.initializeFragsView()
        supportFragmentManager.commit {
            replace(R.id.jokes, frag)
            setReorderingAllowed(true)
            addToBackStack(null)
        }
//        supportFragmentManager.beginTransaction().replace(
//            R.id.jokes, frag, "jokes"
//        ).commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.refresh) {
            frag.refresh()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}