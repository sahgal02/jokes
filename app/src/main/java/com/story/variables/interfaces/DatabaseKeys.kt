package com.story.variables.interfaces

interface DatabaseKeys {
    companion object {
        const val DATABASE_NAME = "jokes_db"
        const val DATABASE_VERSION = 1

       const val TABLE_ITEMS: String = "jokes"
    }
}