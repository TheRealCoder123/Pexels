package com.upnext.pexels.presentation.main_navigation.profile

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.upnext.pexels.common.Resource
import com.upnext.pexels.data.repository.IUserRepository
import com.upnext.pexels.domain.repository.UserRepository
import com.upnext.pexels.domain.use_case.user.GetUserPostsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userRepository: IUserRepository,
    private val getUserPostsUseCase: GetUserPostsUseCase
) : ViewModel() {

    private val _state = mutableStateOf(UserState())
    val state : State<UserState> = _state

    private val _postsState = mutableStateOf(PostsState())
    val postsState : State<PostsState> = _postsState

    init {
        getUserData()
        getUserPosts()
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

    private fun getUserPosts() = viewModelScope.launch {
        val userId = userRepository.getLoggedInUserId()
        getUserPostsUseCase(userId).onEach {
            when(it){
                is Resource.Error -> _postsState.value = PostsState(error = it.message ?: "Unknown Error")
                is Resource.Loading -> _postsState.value = PostsState(areLoading = true)
                is Resource.Success -> _postsState.value = PostsState(posts = it.data!!)
            }
        }.launchIn(this)
    }


}