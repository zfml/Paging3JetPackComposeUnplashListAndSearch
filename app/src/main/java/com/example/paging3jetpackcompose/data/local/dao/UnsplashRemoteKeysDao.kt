package com.example.paging3jetpackcompose.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.paging3jetpackcompose.model.UnsplashImage
import com.example.paging3jetpackcompose.model.UnsplashRemoteKeys
@Dao
interface UnsplashRemoteKeysDao {


    @Query("SELECT * FROM unsplash_keys_table WHERE id =:id")
    suspend fun getRemoteKeys(id: String): UnsplashRemoteKeys


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllRemoteKeys(remoteKeys: List<UnsplashRemoteKeys>)

    @Query("DELETE FROM unsplash_keys_table")
    suspend fun deleteAllRemoteKeys()
}