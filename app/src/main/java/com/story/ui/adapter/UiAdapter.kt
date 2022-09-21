package com.story.ui.adapter

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.story.R
import com.story.base.BaseAdapter
import com.story.base.BaseModel
import com.story.base.BaseViewHolder
import com.story.databinding.AdapterButtonBinding
import com.story.databinding.AdapterEditTextBinding
import com.story.databinding.AdapterItemShimmerBinding
import com.story.databinding.AdapterTextViewBinding
import com.story.jetpacks.entities.UiData
import com.story.jetpacks.entities.UiType
import com.story.utilities.remedoLogs

class UiAdapter(
    private val listener: () -> Unit
) : BaseAdapter<BaseViewHolder<BaseModel>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<BaseModel> {
        return when (viewType) {
            VIEW_SHIMMER -> ShimmerViewHolder(
                AdapterItemShimmerBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            UiType.BUTTON.id-> ButtonViewHolder(parent)
                UiType.EDIT_TEXT.id-> EditTextViewHolder(parent)
            else -> TextViewViewHolder(parent)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (modelList[position] is UiData){
             getType((modelList[position] as UiData).uitype ?: "")
        }else super.getItemViewType(position)
    }

    private fun getType(type: String): Int {
        for (item in UiType.values()) {
            if (type == item.value) {
                return item.id
            }
        }
        return 0
    }

    override fun onBindViewHolder(holder: BaseViewHolder<BaseModel>, position: Int) {
        when (holder) {
            is EditTextViewHolder -> holder.bindTo(modelList[position], position)
            is ButtonViewHolder -> holder.bindTo(modelList[position], position)
            else -> (holder as TextViewViewHolder).bindTo(modelList[position], position)
        }

    }


    inner class ButtonViewHolder(private val binding: AdapterButtonBinding) :
        BaseViewHolder<BaseModel>(binding), View.OnClickListener {

        constructor(
            parent: ViewGroup
        ) : this(
            AdapterButtonBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )


        override fun bindTo(baseModel: BaseModel, position: Int) {
            with(binding) {
                clickListener = this@ButtonViewHolder
                if (baseModel is UiData) {
                    button.text=baseModel.value
                }
            }
        }

        override fun onClick(view: View?) {
            if (view?.id == R.id.button) {
                listener()
            }
        }
    }

    inner class TextViewViewHolder(private val binding: AdapterTextViewBinding) :
        BaseViewHolder<BaseModel>(binding) {

        constructor(
            parent: ViewGroup
        ) : this(
            AdapterTextViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )


        override fun bindTo(baseModel: BaseModel, position: Int) {
            with(binding) {
                if (baseModel is UiData) {
                    label.text = baseModel.value
                }
            }
        }
    }

    inner class EditTextViewHolder(private val binding: AdapterEditTextBinding) :
        BaseViewHolder<BaseModel>(binding) {

        constructor(
            parent: ViewGroup
        ) : this(
            AdapterEditTextBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

        init {
            binding.edit.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun afterTextChanged(p0: Editable?) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    (modelList[absoluteAdapterPosition] as UiData).data=s.toString()
                }
            })
        }

        override fun bindTo(baseModel: BaseModel, position: Int) {
            with(binding) {
                if (baseModel is UiData) {
                    edit.hint = baseModel.hint
                }
            }
        }
    }
}