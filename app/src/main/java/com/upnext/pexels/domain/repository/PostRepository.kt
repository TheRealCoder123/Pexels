package com.upnext.pexels.domain.repository

interface PostRepository {
    suspend fun isFileVideo(url: String) : Boolean
}