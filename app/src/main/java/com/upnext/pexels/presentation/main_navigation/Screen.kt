package com.upnext.pexels.presentation.main_navigation

sealed class Screen(val route: String) {
    object Home : Screen("home_screen")
    object Search : Screen("search_screen")
    object Notification : Screen("notification_screen")
    object Auth : Screen("auth_screen")
    object Create : Screen("create_screen")
    object PostViewScreen : Screen("post_view_screen")
}
