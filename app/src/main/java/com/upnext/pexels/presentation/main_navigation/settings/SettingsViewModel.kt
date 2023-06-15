package com.upnext.pexels.presentation.main_navigation.settings

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import com.upnext.pexels.common.Constants
import com.upnext.pexels.common.Resource
import com.upnext.pexels.data.repository.IUserRepository
import com.upnext.pexels.presentation.main_navigation.profile.PostsState
import com.upnext.pexels.presentation.main_navigation.profile.UserState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val userRepository: IUserRepository,
    private val auth: FirebaseAuth
    ) : ViewModel() {


    private val _state = mutableStateOf(UserState())
    val state : State<UserState> = _state

    init {
        getUserData()
    }

    private fun getUserData() = viewModelScope.launch {
        val userId = userRepository.getLoggedInUserId()
        userRepository.getUserData(userId).onEach {
            when(it){
                is Resource.Error -> _state.value = UserState(error = it.message ?: "Unknown error")
                is Resource.Loading -> _state.value = UserState(isLoading = true)
                is Resource.Success -> _state.value = UserState(user = it.data)
            }
        }.launchIn(this)
    }

    fun logOut() {
        auth.signOut()
    }




}