package com.upnext.pexels.presentation.main_navigation.auth.view_models

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.upnext.pexels.common.Resource
import com.upnext.pexels.data.remote.LoginParams
import com.upnext.pexels.domain.use_case.login.LoginUseCase
import com.upnext.pexels.presentation.main_navigation.auth.states.LoginState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    val emailTextFieldState = mutableStateOf("")
    val passwordTextFieldState = mutableStateOf("")

    private val _state = mutableStateOf(LoginState())
    val state : State<LoginState> = _state

    fun login(loginParams: LoginParams){
        loginUseCase(loginParams).onEach {
            when(it){
                is Resource.Error -> {
                    _state.value = LoginState(error = it.message ?: "Unknown Error")
                }
                is Resource.Loading -> {
                    _state.value = LoginState(isLoading = true)
                }
                is Resource.Success -> {
                    _state.value = LoginState(isLoggedIn = true)
                }
            }
        }.launchIn(viewModelScope)
    }

}