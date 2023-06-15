package com.upnext.pexels.presentation.main_navigation.profile

import com.upnext.pexels.data.remote.Post

data class PostsState (
    val areLoading: Boolean = false,
    val posts: List<Post> = emptyList(),
    val error: String = ""
        )