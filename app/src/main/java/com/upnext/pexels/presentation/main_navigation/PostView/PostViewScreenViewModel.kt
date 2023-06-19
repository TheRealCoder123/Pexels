package com.upnext.pexels.presentation.main_navigation.PostView

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.upnext.pexels.common.Constants.PARAM_POST_ID
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PostViewScreenViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(PostViewState())
    val state : State<PostViewState> = _state

    init {
        savedStateHandle.get<String>(PARAM_POST_ID)?.let {postId->
            getPost(postId)
        }
    }

    private fun getPost(postId: String) {
        Log.e("post id", "$postId")
    }

}