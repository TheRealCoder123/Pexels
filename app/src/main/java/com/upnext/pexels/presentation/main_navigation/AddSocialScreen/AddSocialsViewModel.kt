package com.upnext.pexels.presentation.main_navigation.AddSocialScreen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.upnext.pexels.common.Constants
import com.upnext.pexels.common.Resource
import com.upnext.pexels.common.SocialUserPreferences
import com.upnext.pexels.common.getSocialUserPreference
import com.upnext.pexels.domain.use_case.user.UpdateUserProfileUseCase
import com.upnext.pexels.presentation.main_navigation.ChangeProfile.UpdateUserProfileState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class AddSocialsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val updateUserUseCase: UpdateUserProfileUseCase,
) : ViewModel() {


    private val _updateState = mutableStateOf(UpdateUserProfileState())
    val updateState : State<UpdateUserProfileState> = _updateState

    val textState = mutableStateOf("")
    val whatSocialState = mutableStateOf(SocialUserPreferences.INSTAGRAM)

    init {
        savedStateHandle.get<String>(Constants.PARAM_WHAT_SOCIAL_SHOULD_ADD)?.let {
            whatSocialState.value = getSocialUserPreference(it)
        }
    }

    fun updateUserData(userHashMap: HashMap<String, Any>, emitOnImageUpdate: Boolean = false) {
        updateUserUseCase.invoke(userHashMap).onEach {
            when (it) {
                is Resource.Error -> _updateState.value =
                    UpdateUserProfileState(error = it.message ?: "Unknown error")

                is Resource.Loading -> _updateState.value = UpdateUserProfileState(loading = true)

                is Resource.Success -> _updateState.value = UpdateUserProfileState(done = it.data!!)
            }
        }.launchIn(viewModelScope)

    }
}