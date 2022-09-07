package com.story.jetpacks.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.story.base.BaseModel
import com.story.variables.interfaces.DatabaseKeys

@Entity(tableName = DatabaseKeys.TABLE_ITEMS)
data class ItemModel(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,
    @ColumnInfo(name = "joke")
    val joke: String? = "",
) : BaseModel() {
}
