package com.upnext.pexels.presentation.main_navigation.auth.states

data class LoginState (
    val isLoading: Boolean = false,
    val isLoggedIn: Boolean = false,
    val error: String = ""
        )