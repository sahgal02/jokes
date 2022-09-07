package com.story.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.story.base.BaseAdapter
import com.story.base.BaseModel
import com.story.base.BaseViewHolder
import com.story.databinding.AdapterItemBinding
import com.story.databinding.AdapterItemShimmerBinding
import com.story.jetpacks.entities.ItemModel
import com.story.variables.enums.ActionType

class ItemAdapter(
    private val listener: (model: ItemModel, actionType: ActionType) -> Unit,
) : BaseAdapter<BaseViewHolder<BaseModel>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<BaseModel> {
        return if (viewType == VIEW_SHIMMER)
            ShimmerViewHolder(
                AdapterItemShimmerBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        else ViewHolder(parent)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<BaseModel>, position: Int) {
        if (holder is ViewHolder)
            holder.bindTo(modelList[position], position)
    }


    inner class ViewHolder(private val binding: AdapterItemBinding) :
        BaseViewHolder<BaseModel>(binding) {

        constructor(
            parent: ViewGroup
        ) : this(
            AdapterItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

        override fun bindTo(baseModel: BaseModel, position: Int) {
            with(binding) {
                if (baseModel is ItemModel) {
                    joke.text = baseModel.joke ?: ""
                    divider.isVisible = position != size - 1
                }
            }
        }
    }
}