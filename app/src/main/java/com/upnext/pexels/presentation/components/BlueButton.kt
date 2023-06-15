package com.upnext.pexels.presentation.components

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

@Composable
fun BlueButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit
    ) {

    Button(
        onClick = onClick,
        shape = RoundedCornerShape(5.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF0077b6)),
        contentPadding = PaddingValues(horizontal = 10.dp, vertical = 5.dp),
        modifier = modifier,
    ) {
        Text(text = text, color = Color.White)
    }

}

@Preview
@Composable
fun BlueButtonPreview() {
    BlueButton(text = "Submit"){

    }
}