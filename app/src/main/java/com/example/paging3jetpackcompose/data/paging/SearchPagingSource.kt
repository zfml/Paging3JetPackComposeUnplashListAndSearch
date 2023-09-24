package com.example.paging3jetpackcompose.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.paging3jetpackcompose.data.local.dao.UnsplashDatabase
import com.example.paging3jetpackcompose.data.remote.UnsplashApi
import com.example.paging3jetpackcompose.model.UnsplashImage
import com.example.paging3jetpackcompose.util.Constants.ITEM_PER_PAGE
import javax.inject.Inject

class SearchPagingSource @Inject constructor(
    private val unsplashApi: UnsplashApi,
    private val query: String
): PagingSource<Int,UnsplashImage>(){

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UnsplashImage> {
        val currentPage = params.key ?: 1

        return try {
            val response = unsplashApi.searchImages(query = query, per_page = ITEM_PER_PAGE )

            val endOfPaginationReached = response.results.isEmpty()
            if(response.results.isNotEmpty()) {
                LoadResult.Page(
                    data = response.results,
                    prevKey = if(currentPage == 1) null else currentPage - 1,
                    nextKey = if(endOfPaginationReached) null else currentPage + 1
                )
            } else {
                LoadResult.Page(
                    data = emptyList(),
                    prevKey = null,
                    nextKey = null
                )
            }

        }catch (e: Exception) {
            LoadResult.Error(e)
        }

    }


    override fun getRefreshKey(state: PagingState<Int, UnsplashImage>): Int? {
        return state.anchorPosition
    }


}