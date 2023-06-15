package com.upnext.pexels.presentation.main_navigation.auth.view_models

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.upnext.pexels.common.Resource
import com.upnext.pexels.data.remote.RegisterParams
import com.upnext.pexels.domain.use_case.register.RegisterUserUseCase
import com.upnext.pexels.presentation.main_navigation.auth.states.RegisterUserState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class RegisterWithEmailViewModel @Inject constructor(
    private val registerUserUseCase: RegisterUserUseCase
) : ViewModel() {

    val firstNameTextFieldState = mutableStateOf("")
    val lastNameTextFieldState = mutableStateOf("")
    val emailTextFieldState = mutableStateOf("")
    val passwordTextFieldState = mutableStateOf("")

    private val _state = mutableStateOf(RegisterUserState())
    val state : State<RegisterUserState> = _state

    fun register(registerParams: RegisterParams){
        registerUserUseCase(registerParams).onEach {
            when(it){
                is Resource.Error -> _state.value = RegisterUserState(error = it.message ?: "Unknown Error")
                is Resource.Loading -> _state.value = RegisterUserState(isLoading = true)
                is Resource.Success -> _state.value = RegisterUserState(accountCreated = it.data ?: true)
            }
        }.launchIn(viewModelScope)
    }

}