package com.example.paging3jetpackcompose.data.repository

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.paging3jetpackcompose.data.local.dao.UnsplashDatabase
import com.example.paging3jetpackcompose.data.paging.SearchPagingSource
import com.example.paging3jetpackcompose.data.paging.UnsplashRemoteMediator
import com.example.paging3jetpackcompose.data.remote.UnsplashApi
import com.example.paging3jetpackcompose.model.UnsplashImage
import com.example.paging3jetpackcompose.util.Constants.ITEM_PER_PAGE
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class Repository @Inject constructor(
    private val unsplashApi: UnsplashApi,
    private val unsplashDatabase: UnsplashDatabase
) {

    fun getAllImages(): Flow<PagingData<UnsplashImage>> {

        val pagingSourceFactory = { unsplashDatabase.unsplashImageDao().getAllImages()}
        return Pager(
            config = PagingConfig( pageSize = ITEM_PER_PAGE),
            remoteMediator = UnsplashRemoteMediator(
                unsplashApi = unsplashApi,
                unsplashDatabase = unsplashDatabase
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow

    }


    fun searchImages(query: String): Flow<PagingData<UnsplashImage>> {
        return Pager(
            config = PagingConfig(pageSize = ITEM_PER_PAGE),
            pagingSourceFactory = {
                SearchPagingSource(unsplashApi,query)
            }
        ).flow

    }

}