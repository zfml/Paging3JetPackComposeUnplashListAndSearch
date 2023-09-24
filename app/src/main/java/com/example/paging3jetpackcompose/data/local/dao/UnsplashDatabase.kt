package com.example.paging3jetpackcompose.data.local.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.paging3jetpackcompose.model.UnsplashImage
import com.example.paging3jetpackcompose.model.UnsplashRemoteKeys

@Database(
    entities = [UnsplashImage::class,UnsplashRemoteKeys::class],
    version = 1,
    exportSchema = false
)
abstract class UnsplashDatabase: RoomDatabase() {

    abstract fun unsplashImageDao(): UnsplashImageDao
    abstract fun unsplashImageKeysDao(): UnsplashRemoteKeysDao

}