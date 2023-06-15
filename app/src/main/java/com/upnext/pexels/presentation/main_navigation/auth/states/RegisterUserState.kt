package com.upnext.pexels.presentation.main_navigation.auth.states

data class RegisterUserState(
    val isLoading: Boolean = false,
    val accountCreated: Boolean = false,
    val error: String = ""
)
