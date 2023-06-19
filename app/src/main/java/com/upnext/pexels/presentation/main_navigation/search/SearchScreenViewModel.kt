package com.upnext.pexels.presentation.main_navigation.search

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.capitalize
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.upnext.pexels.common.Resource
import com.upnext.pexels.common.getCategories
import com.upnext.pexels.domain.model.Section
import com.upnext.pexels.domain.use_case.search.GetPostsByCategoryUseCase
import com.upnext.pexels.domain.use_case.search.GetPostsByTagUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class SearchScreenViewModel @Inject constructor(
    private val categoryUseCase: GetPostsByCategoryUseCase,
    private val tagUseCase: GetPostsByTagUseCase
) : ViewModel() {

    private val _state = mutableStateOf(SearchScreenState())
    val state : State<SearchScreenState> = _state

    init {
        getSearchScreenData()
    }

    private fun getSearchScreenData() = viewModelScope.launch {

        _state.value = SearchScreenState(isLoading = true)



        val sections = mutableListOf<Section>()

        getCategories().forEach {category->
            categoryUseCase(category).collect {resource->
                when(resource){
                    is Resource.Error -> {}
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        resource.data?.let { posts ->
                            sections.add(Section(category.category.replaceFirstChar { char ->
                                if (char.isLowerCase()) char.titlecase(
                                    Locale.getDefault()
                                ) else char.toString()
                            }, posts))
                        }
                    }
                }
            }
        }

        _state.value = SearchScreenState(sections = sections)



    }


}