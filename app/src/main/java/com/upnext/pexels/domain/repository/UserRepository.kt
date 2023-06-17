package com.upnext.pexels.domain.repository

import android.net.Uri
import com.upnext.pexels.common.Resource
import com.upnext.pexels.data.remote.Post
import com.upnext.pexels.data.remote.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun getUserData(userId: String) : Flow<Resource<User>>
    suspend fun getLoggedInUserId() : String
    suspend fun getUserPosts(userId: String) : List<Post>
    suspend fun updateUserProfile(userHashMap: HashMap<String, Any>)


}