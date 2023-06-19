package com.upnext.pexels.domain.model

import com.upnext.pexels.data.remote.Post

data class Section (
    val title: String,
    val data: List<Post>
)