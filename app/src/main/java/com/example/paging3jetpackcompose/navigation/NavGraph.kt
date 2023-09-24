package com.example.paging3jetpackcompose.navigation

import android.net.wifi.hotspot2.pps.HomeSp
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.paging3jetpackcompose.screen.home.HomeScreen
import com.example.paging3jetpackcompose.screen.search.SearchScreen

@Composable
fun setUpNavGraph( navController: NavHostController) {

    NavHost(
        navController =  navController,
        startDestination = Screen.Home.route
    ) {
        composable(
            route = Screen.Home.route
        ) {
            HomeScreen(navController = navController)
        }

        composable(
            route =  Screen.Search.route
        ) {
            SearchScreen(navController = navController)
        }
    }

}