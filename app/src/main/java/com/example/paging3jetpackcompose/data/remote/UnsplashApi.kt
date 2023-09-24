package com.example.paging3jetpackcompose.data.remote

import com.example.paging3jetpackcompose.BuildConfig
import com.example.paging3jetpackcompose.model.SearchResult
import com.example.paging3jetpackcompose.model.UnsplashImage
import com.google.gson.internal.GsonBuildConfig
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query


interface UnsplashApi  {

//    @Headers("Authorization: Client-ID ${BuildConfig.API_KEY}")
    @GET("/photos")
    suspend fun getAllImages(
        @Query("page")
        page: Int,
        @Query("per_page")
        per_page: Int
    ): List<UnsplashImage>

//    @Headers("Authorization: Client-ID ${BuildConfig.API_KEY}")
    @GET("/search/photos")
    suspend fun searchImages(
        @Query("query")
        query: String,
        @Query("per_page")
        per_page: Int
    ): SearchResult

}