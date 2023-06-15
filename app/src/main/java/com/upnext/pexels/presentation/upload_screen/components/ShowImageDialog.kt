package com.upnext.pexels.presentation.upload_screen.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.upnext.pexels.R

@Composable
@OptIn(ExperimentalGlideComposeApi::class)
fun ShowImageDialog(
    image: Any,
    onDismissRequest: () -> Unit
) {
    
    Dialog(onDismissRequest = { onDismissRequest() }) {

        Surface(
            shape = RoundedCornerShape(20.dp)
        ) {


            Box {

                GlideImage(model = image, contentDescription = "Image")

            }

        }

    }
    
}

@Composable
@Preview
fun ShowImageDialogPreview() {
    ShowImageDialog(image = R.drawable.auth_bg){}
}