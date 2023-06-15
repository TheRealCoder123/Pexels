package com.upnext.pexels.domain.use_case.user

import com.upnext.pexels.common.Resource
import com.upnext.pexels.data.remote.Post
import com.upnext.pexels.data.repository.IUserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetUserPostsUseCase @Inject constructor(
    private val userRepository: IUserRepository
) {
    operator fun invoke(userId: String) : Flow<Resource<List<Post>>> = flow {
        emit(Resource.Loading())
        try {
            val userPosts = userRepository.getUserPosts(userId)
            emit(Resource.Success(userPosts))
        }catch (e: Exception){
            emit(Resource.Error(null, e.localizedMessage ?: "Unknown Error"))
        }
    }
}