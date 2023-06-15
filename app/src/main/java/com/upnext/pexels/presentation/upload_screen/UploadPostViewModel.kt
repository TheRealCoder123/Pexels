package com.upnext.pexels.presentation.upload_screen

import android.net.Uri
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.upnext.pexels.common.Category
import com.upnext.pexels.common.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UploadPostViewModel @Inject constructor(
) : ViewModel() {


    val selectedMedia = mutableStateListOf<Uri>()
    val currentSelectItem = mutableStateOf<Uri?>(null)
    val selectedCategories = mutableStateListOf<Category>()

    val locationTextFieldState = mutableStateOf("")
    val tagsTextFieldState = mutableStateOf("")




}