package com.upnext.pexels.presentation.main_navigation.ChangeProfile

import android.net.Uri
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.storage.FirebaseStorage
import com.upnext.pexels.common.Resource
import com.upnext.pexels.common.Storage
import com.upnext.pexels.data.repository.IUserRepository
import com.upnext.pexels.domain.use_case.user.UpdateUserProfileUseCase
import com.upnext.pexels.presentation.main_navigation.profile.UserState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@HiltViewModel
class ChangeProfileViewModel @Inject constructor(
    private val userRepository: IUserRepository,
    private val updateUserUseCase: UpdateUserProfileUseCase,
    private val storage: FirebaseStorage
) : ViewModel() {

    val firstNameState = mutableStateOf("")
    val lastNameState = mutableStateOf("")
    val shortBioNameState = mutableStateOf("")
    val tagState = mutableStateOf("")

    val imageUri = mutableStateOf("")
    val isImageChanged = mutableStateOf(false)

    private val _state = mutableStateOf(UserState())
    val state : State<UserState> = _state

    private val _updateState = mutableStateOf(UpdateUserProfileState())
    val updateState : State<UpdateUserProfileState> = _updateState

    private val _updateImageState = mutableStateOf(UpdateUserProfileState())
    val updateImageState : State<UpdateUserProfileState> = _updateImageState

    init {
        getUserData()
    }

    private fun getUserData() = viewModelScope.launch {
        val userId = userRepository.getLoggedInUserId()
        userRepository.getUserData(userId).onEach {
            when(it){
                is Resource.Error -> _state.value = UserState(error = it.message ?: "Unknown error")
                is Resource.Loading -> _state.value = UserState(isLoading = true)
                is Resource.Success -> {
                    _state.value = UserState(user = it.data)
                    firstNameState.value = it.data!!.firstName
                    lastNameState.value = it.data.lastname
                    shortBioNameState.value = it.data.bio
                    tagState.value = it.data.tag
                    imageUri.value = it.data.image
                }
            }
        }.launchIn(this)
    }

    fun updateImage() = viewModelScope.launch {
        _updateImageState.value = UpdateUserProfileState(loading = true)
        try {

            storage.reference.child(Storage.Users.folder)
                .child(userRepository.getLoggedInUserId())
                .child("pfp").putFile(Uri.parse(imageUri.value))
                .await().let {
                    val url = it.metadata!!.reference!!.downloadUrl.await()
                    val userHashMap = HashMap<String, Any>()
                    userHashMap["image"] = url
                    updateUserData(userHashMap, true)
                }




        }catch (e: Exception){
            _updateImageState.value = UpdateUserProfileState(error = e.localizedMessage ?: "Unknown error")
        }
    }
    fun updateUserData(userHashMap: HashMap<String, Any>, emitOnImageUpdate: Boolean = false) {
        updateUserUseCase.invoke(userHashMap).onEach {
            when(it){
                is Resource.Error -> {
                    if (!emitOnImageUpdate){
                        _updateState.value = UpdateUserProfileState(error = it.message ?: "Unknown error")
                    }
                }
                is Resource.Loading -> {
                    if (!emitOnImageUpdate){
                        _updateState.value = UpdateUserProfileState(loading = true)
                    }
                }
                is Resource.Success -> {
                    if(!emitOnImageUpdate){
                        _updateState.value = UpdateUserProfileState(done = it.data!!)
                    }else{
                        _updateImageState.value = UpdateUserProfileState(done = true)
                    }
                }

            }
        }.launchIn(viewModelScope)
    }


}