package com.story.jetpacks.entities

import android.os.Parcelable
import android.widget.RadioButton
import com.google.gson.annotations.SerializedName
import com.story.base.BaseModel
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataModel(
    @SerializedName("logo-url")
    val logo: String? = "",
    @SerializedName("heading-text")
    val heading: String? = "",
    @SerializedName("uidata")
    val uiData :List<UiData>? = emptyList()
) : BaseModel()

@Parcelize
data class UiData(
    @SerializedName("uitype")
    val uitype: String? = "",
    @SerializedName("key")
    val key: String? = "",
    @SerializedName("value")
    val value: String? = "",
    @SerializedName("hint")
    val hint: String? = ""
) : BaseModel(){
    var data:String?=null
}

enum class UiType(val id:Int, val value:String){
    TEXT_VIEW(1,"label"),
    EDIT_TEXT(2, "edittext"),
    BUTTON(3,"button")
}