package com.upnext.pexels.domain.use_case.login

import com.upnext.pexels.common.Resource
import com.upnext.pexels.data.remote.LoginParams
import com.upnext.pexels.data.repository.IAuthRepository
import com.upnext.pexels.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val authRepository: IAuthRepository) {
    operator fun invoke(loginParams: LoginParams) : Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading())
        if (authRepository.login(loginParams)){
            emit(Resource.Success(true))
        }else{
            emit(Resource.Error(false, "Something went wrong logging into your account, please try again"))
        }
    }
}