package com.story.ui.frag

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.story.base.BaseFragment
import com.story.databinding.FragUiBinding
import com.story.jetpacks.ItemViewModel
import com.story.jetpacks.entities.DataModel
import com.story.ui.adapter.UiAdapter
import com.story.variables.interfaces.IntentKeys

/**
 * Frag implementation for jokes list view
 */
class UiFrag : BaseFragment() {
    private lateinit var binding: FragUiBinding
    private val viewModel by viewModels<ItemViewModel>()
    private var model : DataModel?=null

    private val adapter = UiAdapter{
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            model=it.getParcelable(IntentKeys.INTENT_FOR_MODEL)
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        if (!this::binding.isInitialized) {
            binding = FragUiBinding.inflate(
                LayoutInflater.from(requireContext()),
                container, false
            )
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeRecyclerView()
        initializeViewModel()
    }


    override fun initializeRecyclerView() {
        super.initializeRecyclerView()
        binding.items.apply {
            this.layoutManager = LinearLayoutManager(requireContext())
            this.adapter = this@UiFrag.adapter
         }
        model?.let {
            adapter.modelList.addAll(it.uiData!!)
            adapter.notifyAdapterDataSetChanged()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        adapter.clearAdapterItem()
        viewModel.cancelApi()
    }

}