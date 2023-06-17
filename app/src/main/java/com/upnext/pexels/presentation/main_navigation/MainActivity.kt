package com.upnext.pexels.presentation.main_navigation

import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.layout.Column
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import com.upnext.pexels.R
import com.upnext.pexels.common.Constants
import com.upnext.pexels.presentation.main_navigation.components.CircleButton
import com.upnext.pexels.presentation.main_navigation.profile.ProfileViewModel
import com.upnext.pexels.presentation.main_navigation.profile.components.CircleImageView
import com.upnext.pexels.presentation.ui.theme.PexelsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            PexelsTheme {

                val profileViewModel : ProfileViewModel = hiltViewModel()

                val navController = rememberNavController()

                val backStackEntry = navController.currentBackStackEntryAsState()



                Scaffold(
                    bottomBar = {
                        BottomNavigation(
                            modifier = Modifier.systemBarsPadding(),
                            backgroundColor = Color.White,
                            elevation = 5.dp
                        ){
                            BottomNavigationItem(
                                selected = Screen.Home.route == backStackEntry.value?.destination?.route,
                                onClick = {
                                    navController.navigate(Screen.Home.route){
                                        popUpTo(Screen.Home.route){
                                            inclusive = true
                                        }
                                    }
                                },
                                icon = {
                                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                        Icon(imageVector = Icons.Default.Home, contentDescription = "Home")
                                    }
                                },
                                selectedContentColor = Color.Black,
                                unselectedContentColor = Color.Gray
                            )
                            BottomNavigationItem(
                                selected = Screen.Search.route == backStackEntry.value?.destination?.route,
                                onClick = {
                                    navController.navigate(Screen.Search.route){
                                        popUpTo(Screen.Search.route){
                                            inclusive = true
                                        }
                                    }
                                },
                                icon = {
                                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                        Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
                                    }
                                },
                                selectedContentColor = Color.Black,
                                unselectedContentColor = Color.Gray
                            )
                            BottomNavigationItem(
                                selected = true,
                                onClick = {
                                    if(Constants.isLoggedIn()){
                                        navController.navigate(Screen.Create.route){
                                            popUpTo(Screen.Create.route){
                                                inclusive = true
                                            }
                                        }
                                    }else{
                                        navController.navigate(Screen.Auth.route){
                                            popUpTo(Screen.Auth.route){
                                                inclusive = true
                                            }
                                        }
                                    }
                                },
                                icon = {
                                    Box(
                                        modifier = Modifier.size(50.dp)
                                    ) {
                                        CircleButton(
                                            color = Color.Black,
                                            contentColor = Color.White
                                        ) {
                                            Icon(
                                                imageVector = Icons.Default.Add,
                                                contentDescription = "Add",
                                                tint = Color.White
                                            )
                                        }
                                    }
                                },
                                selectedContentColor = Color.Black,
                                unselectedContentColor = Color.Gray
                            )
                            BottomNavigationItem(
                                selected = Screen.Notification.route == backStackEntry.value?.destination?.route,
                                onClick = {
                                    navController.navigate(Screen.Notification.route){
                                        popUpTo(Screen.Notification.route){
                                            inclusive = true
                                        }
                                    }
                                },
                                icon = {
                                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                        Icon(imageVector = Icons.Default.Notifications, contentDescription = "Notifications")
                                    }
                                },
                                selectedContentColor = Color.Black,
                                unselectedContentColor = Color.Gray
                            )
                            BottomNavigationItem(
                                selected = Screen.Auth.route == backStackEntry.value?.destination?.route,
                                onClick = {
                                    navController.navigate(Screen.Auth.route){
                                        popUpTo(Screen.Auth.route){
                                            inclusive = true
                                        }
                                    }
                                },
                                icon = {

                                    if (Constants.isLoggedIn()){
                                        profileViewModel.state.value.user?.let {
                                            CircleImageView(
                                                image = it.image.ifEmpty { R.drawable.no_profile_image },
                                                size = 35.dp
                                            )
                                        }

                                        if (profileViewModel.state.value.error.isNotBlank()){
                                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                                Icon(imageVector = Icons.Default.AccountCircle, contentDescription = "Profile")
                                            }
                                        }
                                    }else {
                                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                            Icon(
                                                imageVector = Icons.Default.AccountCircle,
                                                contentDescription = "Profile"
                                            )
                                        }
                                    }
                                },
                                selectedContentColor = Color.Black,
                                unselectedContentColor = Color.Gray
                            )
                        }
                    }
                ) {
                    it
                    Navigation(navController = navController)
                }
            }
        }
    }
}



