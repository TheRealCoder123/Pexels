package com.upnext.pexels.data.remote

import com.upnext.pexels.common.Category
import java.io.Serializable

data class Post (
    val postId: String = "",
    val postUrl: String = "",
    val tags: List<String> = emptyList(),
    val location: String = "",
    val views: Int = 0,
    val downloads: Int = 0,
    val category: List<Category> = emptyList(),
    val userId: String = ""
    )