package com.upnext.pexels.presentation.main_navigation.search

import com.upnext.pexels.domain.model.Section

data class SearchScreenState (
    val isLoading: Boolean = false,
    val sections: List<Section> = emptyList(),
    val error: String = ""
        )