package com.example.paging3jetpackcompose.data.paging

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.paging3jetpackcompose.data.local.dao.UnsplashDatabase
import com.example.paging3jetpackcompose.data.remote.UnsplashApi
import com.example.paging3jetpackcompose.model.UnsplashImage
import com.example.paging3jetpackcompose.model.UnsplashRemoteKeys
import com.example.paging3jetpackcompose.util.Constants.ITEM_PER_PAGE
import java.lang.Exception
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class UnsplashRemoteMediator (
    val unsplashApi: UnsplashApi,
    val unsplashDatabase: UnsplashDatabase
): RemoteMediator<Int, UnsplashImage>() {

    private val unsplashImageDao = unsplashDatabase.unsplashImageDao()
    private val unsplashKeysDao = unsplashDatabase.unsplashImageKeysDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, UnsplashImage>,
    ): MediatorResult {
         return try {
             val currentPage = when(loadType) {
                 LoadType.REFRESH -> {
                     val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                     remoteKeys?.nextPage?.minus(1) ?: 1
                 }
                 LoadType.PREPEND -> {
                     val remoteKeys = getRemoteKeyForFirstItem(state)
                     val prevPage = remoteKeys?.prevPage
                         ?: return MediatorResult.Success(
                             endOfPaginationReached =  remoteKeys != null
                         )
                     prevPage
                 }
                 LoadType.APPEND -> {
                     val remoteKeys = getRemoteKeyForLastItem(state)
                     val nextPage = remoteKeys?.nextPage
                         ?: return MediatorResult.Success(
                             endOfPaginationReached = remoteKeys != null
                         )
                     nextPage
                 }
             }

             val response = unsplashApi.getAllImages(page = currentPage, per_page = ITEM_PER_PAGE)

             val endOfPaginationReached = response.isEmpty()

             val prevPage = if(currentPage == 1) null else currentPage -1
             val nextPage = if(endOfPaginationReached) null else currentPage + 1

             unsplashDatabase.withTransaction{

                 if(loadType == LoadType.REFRESH) {
                     unsplashImageDao.deleteAllImages()
                     unsplashKeysDao.deleteAllRemoteKeys()
                 }



                 val keys = response.map { unsplashImage ->
                     UnsplashRemoteKeys(
                        id = unsplashImage.id,
                         prevPage = prevPage,
                         nextPage = nextPage
                     )
                 }
                 unsplashKeysDao.addAllRemoteKeys(remoteKeys = keys)

                 unsplashImageDao.addImages(images = response)

             }

             MediatorResult.Success(
                 endOfPaginationReached = endOfPaginationReached
             )

         }catch (e: Exception) {
             MediatorResult.Error(e)
         }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, UnsplashImage>
    ): UnsplashRemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                unsplashKeysDao.getRemoteKeys(id)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, UnsplashImage>
    ): UnsplashRemoteKeys? {
        return state.pages.firstOrNull{ it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { unsplashImage ->
                unsplashKeysDao.getRemoteKeys(id = unsplashImage.id)
            }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int , UnsplashImage>
    ): UnsplashRemoteKeys? {

        return state.pages.lastOrNull{ it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { unsplashImage ->
                unsplashKeysDao.getRemoteKeys(id = unsplashImage.id)
            }

    }

}