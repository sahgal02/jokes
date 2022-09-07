package com.story.jetpacks.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.story.jetpacks.dao.ItemDao
import com.story.jetpacks.entities.ItemModel
import com.story.variables.interfaces.DatabaseKeys

/**
 * AppDatabase Class and Table Structure
 *
 * @added : [ItemDao]
 */
@Database(
    entities = [
        ItemModel::class],
    version = DatabaseKeys.DATABASE_VERSION, exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    /**
     * [androidx.room.Dao] representation
     */

    abstract fun itemDao(): ItemDao

    companion object {

        @Volatile
        private var instance: AppDatabase? = null

        /**
         * Getting object
         */
        fun get(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context,
                AppDatabase::class.java, DatabaseKeys.DATABASE_NAME
            )
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}