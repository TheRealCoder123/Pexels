package com.upnext.pexels.presentation.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun GrayBgTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    inputType: KeyboardType
) {

    Box(modifier = Modifier.fillMaxWidth().padding(horizontal = 15.dp)){
        TextField(
            value = value,
            onValueChange = onValueChange,
            label = { Text(
                text = label,
                style = MaterialTheme.typography.body1,
                color = Color(0xFF0077b6)
            ) },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color(0xFFE4E4E4),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                textColor = Color.Black,
                cursorColor = Color(0xFF0077b6)
            ),
            modifier = Modifier
                .padding(vertical = 4.dp)
                .border(width = 1.dp, color = Color(0xFF0077b6), shape = RoundedCornerShape(7.dp))
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = inputType)
        )
    }

}

@Composable
@Preview
fun GrayBgTextFieldPreview() {
    GrayBgTextField(
        value = "Hello World",
        onValueChange = {},
        label = "First name",
        modifier = Modifier,
        inputType = KeyboardType.Password
    )
}