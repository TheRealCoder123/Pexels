package com.upnext.pexels.presentation.main_navigation.profile.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.upnext.pexels.R

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CircleImageView(
    image: Any,
    size: Dp = 150.dp
) {

    GlideImage(
        model = image,
        contentDescription = "Profile Image",
        modifier = Modifier
            .size(size)
            .clip(CircleShape),
        contentScale = ContentScale.Crop
    )
    

}

@Composable
@Preview
fun CircleImagePreview() {
    CircleImageView(image = "")
}