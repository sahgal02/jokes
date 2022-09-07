package com.story.jetpacks.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.story.jetpacks.entities.ItemModel;

import org.jetbrains.annotations.NotNull;

import java.util.List;

@Dao
public interface ItemDao {

    @Transaction
    @Query("SELECT * FROM JOKES")
    List<ItemModel> select();

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ItemModel model);

    @Query("DELETE FROM JOKES")
    void clearAll();
}
