package com.upnext.pexels.common

import androidx.compose.ui.graphics.Color

sealed class Categories(val category: String) {
    object Sunset : Categories("sunset")
    object Office : Categories("office")
    object Business : Categories("business")
    object Space : Categories("space")
    object Mountain : Categories("mountain")
    object Abstract : Categories("abstract")
    object Food : Categories("food")
    object Travel : Categories("travel")
    object Technology : Categories("technology")
    object Landscape : Categories("landscape")
    object Summer : Categories("summer")
    object Beach : Categories("beach")
}

data class Category(
    val category: String = "",
    val color: Long = 0L
)


fun getCategories() : List<Category> {
    return listOf(
        Category(
            Categories.Sunset.category,
            -5591275
        ),
        Category(
            Categories.Office.category,
            -2434344L
        ),
        Category(
            Categories.Business.category,
            -16744320L
        ),
        Category(
            Categories.Space.category,
            -10843596L
        ),
        Category(
            Categories.Mountain.category,
            -8372223L
        ),
        Category(
            Categories.Abstract.category,
            -11837387L
        ),
        Category(
            Categories.Food.category,
            -1690499L
        ),
        Category(
            Categories.Travel.category,
            -135584L
        ),
        Category(
            Categories.Technology.category,
            -10987432L
        ),
        Category(
            Categories.Landscape.category,
            -4282464L
        ),
        Category(
            Categories.Summer.category,
            -15014938L
        ),
        Category(
            Categories.Beach.category,
            -6579313L
        ),
    )
}