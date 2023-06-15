package com.upnext.pexels.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun WhiteGrayOutlinedButton(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
    ) {

    Button(
        onClick = onClick,
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
        contentPadding = PaddingValues(horizontal = 10.dp, vertical = 5.dp),
        modifier = modifier,
        border = BorderStroke(1.dp,Color.Gray)
    ) {
        Text(text = text, color = Color.Red, fontSize = 13.sp)
    }

}

@Composable
@Preview
fun WhiteGrayOutlinedButtonPreview() {
    WhiteGrayOutlinedButton("Delete this media"){

    }
}