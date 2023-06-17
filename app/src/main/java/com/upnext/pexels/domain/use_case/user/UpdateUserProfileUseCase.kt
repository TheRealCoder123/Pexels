package com.upnext.pexels.domain.use_case.user

import com.upnext.pexels.common.Resource
import com.upnext.pexels.data.repository.IUserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UpdateUserProfileUseCase @Inject constructor(
    private val userRepository: IUserRepository
) {
    operator fun invoke(userHashMap: HashMap<String, Any>) : Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading())
        try {
            userRepository.updateUserProfile(userHashMap)
            emit(Resource.Success(true))
        }catch (e: Exception){
            emit(Resource.Error(false, e.localizedMessage ?: "Unknown error"))
        }
    }
}