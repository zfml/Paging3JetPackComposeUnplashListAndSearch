package com.example.paging3jetpackcompose.di

import android.content.Context
import androidx.room.Room
import com.example.paging3jetpackcompose.data.local.dao.UnsplashDatabase
import com.example.paging3jetpackcompose.util.Constants
import com.example.paging3jetpackcompose.util.Constants.UNSPLASH_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): UnsplashDatabase {

        return Room.databaseBuilder(
            context,
            UnsplashDatabase::class.java,
            UNSPLASH_DATABASE
        ).build()

    }

}