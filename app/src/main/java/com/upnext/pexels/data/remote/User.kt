package com.upnext.pexels.data.remote

data class User (
    val userId: String = "",
    val firstName: String = "",
    val lastname: String = "",
    val email: String = "",
    val image: String = "",
    val totalViews: Int = 0,
    val totalDownloads: Int = 0,
    val tag: String = "",
    val instagram: String = "",
    val twitter: String = "",
    val website: String = "",
    val bio: String = ""
        )