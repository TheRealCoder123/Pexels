package com.upnext.pexels.presentation.main_navigation.settings.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.common.io.Files.append

@Composable
fun SettingsNavView(
    modifier: Modifier = Modifier,
    title: String,
    value: String,
    background: Color = Color.White,
    onClick: () -> Unit
) {

    Row(
        modifier = modifier.fillMaxWidth()
            .background(background)
            .padding(PaddingValues(horizontal = 10.dp)),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = title,
            fontSize = 20.sp
        )

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = buildAnnotatedString {
                    val text = value.take(19)
                    append(text)
                    if (value.length > 15) {
                        append("...")
                    }
                },
                color = Color.Gray,
                maxLines = 1
            )
            IconButton(onClick = {
                onClick()
            }) {
                Icon(
                    Icons.Default.ArrowForward,
                    contentDescription = "View",
                    tint = Color.Gray
                )
            }
        }



    }



}

@Composable
@Preview()
fun SettingsNavViewPreview() {
    SettingsNavView(title = "Email", value = "martinsleze09@gmail.com"){

    }
}