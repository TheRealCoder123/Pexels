package com.upnext.pexels.presentation.main_navigation.ChangeProfile

data class UpdateUserProfileState(
    val loading: Boolean = false,
    val done: Boolean = false,
    val error: String = ""
)
