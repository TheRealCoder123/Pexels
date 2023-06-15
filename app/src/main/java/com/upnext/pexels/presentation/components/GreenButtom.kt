package com.upnext.pexels.presentation.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
fun GreenButton(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {

    Button(
        onClick = onClick,
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF66D6AE)),
        contentPadding = PaddingValues(17.dp),
        modifier = modifier.fillMaxWidth(),
    ) {
        Text(text = text, color = Color.White)
    }


}

@Composable
@Preview
fun GreenButtonPreview() {
    GreenButton(text = "Create an account") {

    }
}