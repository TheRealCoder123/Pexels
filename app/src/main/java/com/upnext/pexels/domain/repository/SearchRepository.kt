package com.upnext.pexels.domain.repository

import com.upnext.pexels.common.Category
import com.upnext.pexels.data.remote.Post

interface SearchRepository {

    suspend fun getByCategory(category: Category) : List<Post>
    suspend fun getByTag(tag: String): List<Post>

}