package com.example.paging3jetpackcompose.screen.search

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.paging3jetpackcompose.screen.components.ListContent
import com.example.paging3jetpackcompose.screen.components.UnsplashItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    navController: NavHostController,
    searchViewModel: SearchViewModel = hiltViewModel(),
) {

    val searchQuery by searchViewModel.searchQuery

    val searchImages = searchViewModel.searchImages.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            SearchWidget(
                text = searchQuery,
                onTextChange = {searchViewModel.updateSearchQuery(it)} ,
                onSearchedClicked = {
                    searchViewModel.searchHeroes(searchQuery)
                },
                onClosedClicked = {
                    navController.popBackStack()
                }
            )
        },
        content = { innerPadding ->
            ListContent(modifier = Modifier.padding(innerPadding) , items = searchImages)
        }


    )

}