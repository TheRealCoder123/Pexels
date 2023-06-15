package com.upnext.pexels.presentation.main_navigation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

@Composable
fun CircleButton(
    color: Color,
    contentColor: Color,
    shape: Shape = CircleShape,
    content: @Composable () -> Unit
) {
    Box(
        modifier = Modifier
            .size(64.dp)
            .clip(shape)
            .background(color),
        contentAlignment = Alignment.Center
    ) {
        content()
    }
}






