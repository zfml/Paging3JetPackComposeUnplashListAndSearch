@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.paging3jetpackcompose.screen.home

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.LoadStates
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.example.paging3jetpackcompose.navigation.Screen
import com.example.paging3jetpackcompose.screen.components.ListContent
import com.example.paging3jetpackcompose.screen.components.UnsplashItem

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    navController: NavHostController,
    homeViewModel: HomeViewModel = hiltViewModel(),
) {

    val getAllImages = homeViewModel.getAllImages.collectAsLazyPagingItems()


    Scaffold(
        topBar = {
            HomeTopBar(
                onSearchedClicked = {
                    navController.navigate(Screen.Search.route)
                }
            )
        },
        content = { innerPadding ->
           Box(
               modifier = Modifier
                   .fillMaxSize()
                   .padding(innerPadding)
           ) {

               if(getAllImages.loadState.refresh is LoadState.Loading) {
                   CircularProgressIndicator(
                       modifier = Modifier.align(Alignment.Center)
                   )
               } else {
                   LazyColumn(
                       modifier = Modifier.fillMaxSize(),
                       verticalArrangement = Arrangement.spacedBy(12.dp),
                       horizontalAlignment = Alignment.CenterHorizontally
                   ) {

                       items(
                           items = getAllImages,
                           key = { unsplashImage -> unsplashImage.id }
                       ) { unsplashImage ->
                           unsplashImage?.let { UnsplashItem(unsplashImage = it) }

                       }

                       if (getAllImages.loadState.append is LoadState.Loading) {
                           Log.d("endOfPage", "CircleProgressIndicator")

                       }
//                       item {
//
//                       }

                   }
               }
           }
        }


    )
}