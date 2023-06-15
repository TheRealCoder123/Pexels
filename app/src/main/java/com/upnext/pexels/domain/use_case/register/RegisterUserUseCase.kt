package com.upnext.pexels.domain.use_case.register

import com.upnext.pexels.common.Resource
import com.upnext.pexels.data.remote.RegisterParams
import com.upnext.pexels.data.repository.IAuthRepository
import com.upnext.pexels.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RegisterUserUseCase @Inject constructor(
    private val authRepository: IAuthRepository
) {

    operator fun invoke(registerParams: RegisterParams) : Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading())
        if (authRepository.register(registerParams)){
            emit(Resource.Success(true))
        }else{
            emit(Resource.Error(false, "Something went wrong creating your account, please try again"))
        }
    }
}