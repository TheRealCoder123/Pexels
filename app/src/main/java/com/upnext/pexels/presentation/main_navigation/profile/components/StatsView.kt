package com.upnext.pexels.presentation.main_navigation.profile.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun StatsView(
    modifier: Modifier = Modifier,
    title: String,
    value: String
) {

    Column(
        modifier = modifier.padding(10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
        text = value,
            fontSize = 17.sp,
            color = Color.White
        )

        Text(
            text = title,
            fontSize = 14.sp,
            color = Color.Gray
        )

    }

}

@Composable
@Preview
fun StatsViewPreview() {
    StatsView(title = "Total views", value = "20")
}