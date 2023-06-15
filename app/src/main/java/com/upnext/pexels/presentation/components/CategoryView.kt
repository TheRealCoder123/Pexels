package com.upnext.pexels.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.upnext.pexels.common.Categories
import com.upnext.pexels.common.Category
import java.util.Locale
import kotlin.random.Random

@Composable
fun CategoryView(
    modifier: Modifier = Modifier,
    category: Category,
    onClick: (Category) -> Unit
    ) {

    Box(
        modifier = modifier
            .size(100.dp)
            .clip(RoundedCornerShape(10.dp))
            .clickable {
                onClick(category)
            }.background(Color(category.color)),
        contentAlignment = Alignment.Center
    ){
        Text(
            text = category.category.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() },
            color = Color.White
        )
    }

}

@Composable
@Preview
fun CategoryViewPreview() {
    CategoryView(category = Category(Categories.Beach.category, -2434344L)) {

    }
}