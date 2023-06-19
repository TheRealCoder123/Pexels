package com.upnext.pexels.presentation.main_navigation.PostView

import com.upnext.pexels.data.remote.Post

data class PostViewState(
    val isLoading: Boolean = false,
    val post: Post? = null,
    val error: String = ""
)
