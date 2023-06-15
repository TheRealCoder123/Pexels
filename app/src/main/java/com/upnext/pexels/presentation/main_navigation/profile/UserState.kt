package com.upnext.pexels.presentation.main_navigation.profile

import com.upnext.pexels.data.remote.User

data class UserState (
    val isLoading: Boolean = false,
    val user: User? = null,
    val error: String = ""
        )