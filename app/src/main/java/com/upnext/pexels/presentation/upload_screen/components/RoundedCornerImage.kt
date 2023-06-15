package com.upnext.pexels.presentation.upload_screen.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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
fun RoundedCornerImage(
    image: Any,
    size: Dp,
    modifier: Modifier = Modifier,
    isVideo: Boolean = false,
    showIcons: Boolean = false
) {
    Box {
        GlideImage(
            model = image,
            contentDescription = "Post Image",
            modifier = modifier
                .size(size)
                .clip(RoundedCornerShape(20.dp)),
            contentScale = ContentScale.Crop,
        )
        if(showIcons){
            if (isVideo){
                Icon(
                    painter = painterResource(id = R.drawable.baseline_video_library_24),
                    contentDescription = "Is Video",
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(10.dp),
                    tint = Color.LightGray
                )
            }else{
                Icon(
                    painter = painterResource(id = R.drawable.baseline_image_24),
                    contentDescription = "Is Video",
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(10.dp),
                    tint = Color.LightGray
                )
            }
        }
    }
}

@Preview
@Composable
fun RoundedCornerImagePreview() {
    RoundedCornerImage(R.drawable.pexels_logo, size = 300.dp, isVideo = true)
}