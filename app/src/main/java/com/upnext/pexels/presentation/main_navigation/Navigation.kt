package com.upnext.pexels.presentation.main_navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.upnext.pexels.common.Constants.PARAM_POST_ID
import com.upnext.pexels.presentation.main_navigation.PostView.PostViewScreen
import com.upnext.pexels.presentation.main_navigation.auth.AuthScreen
import com.upnext.pexels.presentation.main_navigation.home.HomeScreen
import com.upnext.pexels.presentation.main_navigation.notifications.NotificationScreen
import com.upnext.pexels.presentation.main_navigation.search.SearchScreen
import com.upnext.pexels.presentation.upload_screen.UploadPostScreen

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Home.route){
        composable(Screen.Home.route){
            HomeScreen(navController = navController)
        }
        composable(Screen.Search.route){
            SearchScreen { postId ->
                navController.navigate(Screen.PostViewScreen.route + "/${postId}")
            }
        }
        composable(Screen.Notification.route){
            NotificationScreen()
        }
        composable(Screen.Auth.route){
            AuthScreen()
        }
        composable(Screen.Create.route){
            UploadPostScreen(navController = navController)
        }
        composable(Screen.PostViewScreen.route + "/{${PARAM_POST_ID}}"){
            PostViewScreen(navController)
        }
    }
}