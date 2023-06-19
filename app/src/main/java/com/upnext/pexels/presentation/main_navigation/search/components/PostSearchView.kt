package com.upnext.pexels.presentation.main_navigation.search.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.upnext.pexels.data.remote.Post

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun PostSearchView(
    post: Post,
    onPostClick: (Post) -> Unit
) {

    val isGradientVisible = remember {
        mutableStateOf(true)
    }

    Card(
        modifier = Modifier
            .width(150.dp)
            .height(200.dp)
            .padding(5.dp),
        shape = RoundedCornerShape(15.dp),
    ) {
        Box(
            modifier = Modifier.clickable {
                onPostClick(post)
            }
        ){



            GlideImage(
                model = post.postUrl,
                contentDescription = "Post image",
                contentScale = ContentScale.Crop,
                modifier = Modifier.align(Alignment.Center)
            )

           if (isGradientVisible.value){
               Box(
                   modifier = Modifier
                       .fillMaxSize()
                       .background(
                           Brush.verticalGradient(
                               colors = listOf(
                                   Color.Transparent,
                                   Color.Black
                               ),
                               startY = 300f
                           )
                       )
               )
           }

            Box(modifier = Modifier
                .padding(vertical = 10.dp)
                .align(Alignment.BottomCenter)){


                if(post.location.isNotBlank()){
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(10.dp)
                    ) {
                        Icon(imageVector = Icons.Default.LocationOn, contentDescription = "Post Location", tint = Color.White)

                        Spacer(modifier = Modifier.width(5.dp))

                        Text(
                            text = post.location,
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            color = Color.White,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }else if(post.tags.isNotEmpty()){
                    Text(
                        text = buildAnnotatedString {
                               post.tags.forEach { tag ->
                                   append(tag)
                                   if (post.tags.last() != tag){
                                       append(", ")
                                   }
                               }
                        },
                        color = Color.White,
                        modifier = Modifier.padding(5.dp),
                        maxLines = 3
                    )
                }else{
                    isGradientVisible.value = false
                }

            }



        }
    }


}
