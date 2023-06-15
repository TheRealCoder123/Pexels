package com.upnext.pexels.presentation.main_navigation.auth

sealed class AuthScreens(val route: String) {

    object LoginOptionsScreen : AuthScreens("login_options_screen")
    object RegisterWithEmailScreen : AuthScreens("register_with_email_screen")
    object LoginScreen : AuthScreens("login_screen")
    object ProfileScreen : AuthScreens("profile_screen")
    object SettingsScreen : AuthScreens("settings_screen")
    object PersonalInfoScreen : AuthScreens("personal_info_screen")

}