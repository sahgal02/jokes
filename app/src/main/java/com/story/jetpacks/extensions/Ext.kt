package com.story.jetpacks.extensions

import android.widget.ImageView
import androidx.constraintlayout.widget.Placeholder
import com.bumptech.glide.Glide

fun ImageView.loadImage(url:String?, placeholder: Int){
    url?.let {
        Glide.with(this.context).load(it)
            .placeholder(placeholder)
            .into(this)
    }
}