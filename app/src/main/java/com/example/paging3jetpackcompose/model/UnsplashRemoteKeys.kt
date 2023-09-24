package com.example.paging3jetpackcompose.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.paging3jetpackcompose.util.Constants.UNSPLASH_REMOTE_KEY_TABLE

@Entity(tableName = UNSPLASH_REMOTE_KEY_TABLE)
data class UnsplashRemoteKeys(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val prevPage: Int?,
    val nextPage: Int?
)
