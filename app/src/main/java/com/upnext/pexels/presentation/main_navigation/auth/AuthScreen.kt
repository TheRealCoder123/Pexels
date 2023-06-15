package com.upnext.pexels.presentation.main_navigation.auth

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.upnext.pexels.common.Constants
import com.upnext.pexels.presentation.main_navigation.ChangeProfile.ChangeProfileScreen
import com.upnext.pexels.presentation.main_navigation.profile.ProfileScreen
import com.upnext.pexels.presentation.main_navigation.settings.SettingsScreen

@Composable
fun AuthScreen() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = if (Constants.isLoggedIn()) AuthScreens.ProfileScreen.route else AuthScreens.LoginOptionsScreen.route
    ){
        composable(AuthScreens.LoginOptionsScreen.route) {
            LoginOptionsScreen(navController)
        }
        composable(AuthScreens.RegisterWithEmailScreen.route) {
            RegisterWithEmailScreen(navController = navController)
        }
        composable(AuthScreens.LoginScreen.route) {
            LoginScreen(navController = navController)
        }
        composable(AuthScreens.ProfileScreen.route) {
            ProfileScreen(navController = navController)
        }
        composable(AuthScreens.SettingsScreen.route) {
            SettingsScreen(navController = navController)
        }
        composable(AuthScreens.PersonalInfoScreen.route) {
            ChangeProfileScreen {
                navController.navigateUp()
            }
        }
    }

}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun AuthScreenPreview() {
    AuthScreen()
}