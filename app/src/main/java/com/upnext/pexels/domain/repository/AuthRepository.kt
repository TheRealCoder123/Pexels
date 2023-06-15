package com.upnext.pexels.domain.repository

import com.upnext.pexels.data.remote.LoginParams
import com.upnext.pexels.data.remote.RegisterParams
import com.upnext.pexels.data.remote.User

interface AuthRepository {

    suspend fun register(registerParams: RegisterParams) : Boolean
    suspend fun login(loginParams: LoginParams) : Boolean

}