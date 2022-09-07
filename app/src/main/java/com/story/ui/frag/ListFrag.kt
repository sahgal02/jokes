package com.story.ui.frag

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.story.base.BaseFragment
import com.story.base.BaseModel
import com.story.databinding.FragJokesBinding
import com.story.jetpacks.ItemViewModel
import com.story.jetpacks.entities.ItemModel
import com.story.jetpacks.source.network.impl.ItemRemoteSourceImpl
import com.story.ui.adapter.ItemAdapter
import com.story.utilities.Utils
import com.story.variables.abstracts.OnEventTriggerListener
import com.story.variables.interfaces.Constants

/**
 * Frag implementation for jokes list view
 */
class ListFrag : BaseFragment() {
    private lateinit var binding: FragJokesBinding
    private val viewModel by viewModels<ItemViewModel>()
    private val handler = Handler(Looper.getMainLooper())
    private val runnable = Runnable {
        if (hasConnection()) {
            requireActivity().runOnUiThread {
                adapter.addShimmerList(1)
                binding.items.scrollToPosition(adapter.size - 1)
            }
            initializeViewModel()
        }
    }
    private val adapter = ItemAdapter { itemModel, actionType -> }

    private fun startJob() {
        handler.postDelayed(runnable, Constants.THREAD_DELAY)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        if (!this::binding.isInitialized) {
            binding = FragJokesBinding.inflate(
                LayoutInflater.from(requireContext()),
                container, false
            )
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpObservers()
        initializeRecyclerView()
    }

    private fun setUpObservers() {
        adapter.addShimmerList(6)
        viewModel.items.observe(viewLifecycleOwner) {
            adapter.clearShimmerList()
            if (!it.isNullOrEmpty()) {
                val models = arrayListOf<BaseModel>()
                models.addAll(it)
                adapter.notifyAdapterDataSetChanged(models)
                startJob()
            } else {
                if (hasConnection()) {
                    adapter.addShimmerList(6)
                    initializeViewModel()
                }
            }
        }
        viewModel.select()
    }

    override fun initializeRecyclerView() {
        super.initializeRecyclerView()
        binding.items.apply {
            this.layoutManager = LinearLayoutManager(requireContext())
            this.adapter = this@ListFrag.adapter
        }
    }

    /**
     * Api call to fetch list of items : [ItemRemoteSourceImpl.apiFetchItems]
     */
    override fun initializeViewModel() {
        super.initializeViewModel()
        if (!isUploading()) {
            setUploadStatus(true)
            viewModel.fetchItems(object : OnEventTriggerListener() {
                override fun onApiSuccess(any: Any?) {
                    super.onApiSuccess(any)
                    setUploadStatus(false)
                    if (adapter.size > Constants.VALUE_PAGE) {
                        viewModel.clear()
                        adapter.clearAdapterItem()
                    } else
                        adapter.clearShimmerList()
                    adapter.notifyAdapterItemInsert(any as ItemModel)
                    binding.items.scrollToPosition(adapter.size - 1)
                    if (adapter.size > 1) {
                        adapter.notifyItemChanged(adapter.size - 2)
                    }
                    startJob()
                }

                override fun onErrorMessage(statusCode: Int, errorMessage: String?) {
                    super.onErrorMessage(statusCode, errorMessage)
                    adapter.clearShimmerList()
                    startJob()
                    Utils.snackbar(requireActivity(), errorMessage)
                }
            })
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        adapter.clearAdapterItem()
        viewModel.cancelApi()
    }

    fun refresh() {
        if (hasConnection()) {
            setUploadStatus(false)
            viewModel.clear()
            adapter.clearAdapterItem()
            adapter.addShimmerList(10)
            initializeViewModel()
        }
    }
}