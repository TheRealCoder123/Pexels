package com.upnext.pexels.presentation.main_navigation.profile

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import com.upnext.pexels.R
import com.upnext.pexels.presentation.main_navigation.Screen
import com.upnext.pexels.presentation.main_navigation.auth.AuthScreens
import com.upnext.pexels.presentation.main_navigation.profile.components.CircleImagePreview
import com.upnext.pexels.presentation.main_navigation.profile.components.CircleImageView
import com.upnext.pexels.presentation.main_navigation.profile.components.StatsView
import com.upnext.pexels.presentation.upload_screen.components.RoundedCornerImage
import com.upnext.pexels.presentation.upload_screen.other.Media
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = hiltViewModel(),
    navController: NavController
) {

    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val state = viewModel.state.value
    val postsState = viewModel.postsState.value

    Box(modifier = Modifier
        .fillMaxSize()
        .padding(bottom = 85.dp)){

        LazyColumn(
            modifier = Modifier
                .background(Color.Black)
                .padding(15.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            item {
                state.user?.let { user->

                    TopAppBar(
                        title = {
                            Text(
                                text = user.tag,
                                color = Color.White
                            )
                        },
                        colors = TopAppBarDefaults.largeTopAppBarColors(containerColor = Color.Black),
                        actions = {
                            IconButton(onClick = {
                                navController.navigate(AuthScreens.SettingsScreen.route)
                            }) {
                                Icon(Icons.Default.Settings, contentDescription = "Settings", tint = Color.White)
                            }
                        }
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Box(modifier = Modifier.padding(10.dp)){
                        CircleImageView(
                            user.image.ifBlank {
                                R.drawable.no_profile_image
                            }
                        )
                    }

                    Text(
                        modifier = Modifier,
                        text = "${user.firstName} ${user.lastname}",
                        fontSize = 25.sp,
                        color = Color.White
                    )

                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {

                        StatsView(
                            title = "Views",
                            value = user.totalViews.toString()
                        )

                        StatsView(
                            title = "Downloads",
                            value = user.totalDownloads.toString()
                        )

                        StatsView(
                            title = "Collections",
                            value = "0"
                        )

                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 5.dp)
                    ){
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = if(postsState.areLoading)
                                "Loading Posts..."
                            else if(postsState.error.isNotBlank())
                                postsState.error
                            else "Your Posts",
                            fontSize = 25.sp,
                            color = Color.White,
                            textAlign = TextAlign.Start
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))



                }
            }

            items(postsState.posts){
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(10.dp)
                ){

                    RoundedCornerImage(
                        image = it.postUrl,
                        size = 0.dp,
                        modifier = Modifier
                            .fillMaxSize()
                    )

                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .fillMaxWidth()
                            .background(Color(0xA41F1F1F))
                    )

                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                        Row {
                            StatsView(title = "Video Views", value = it.views.toString())
                            StatsView(title = "Video Downloads", value = it.downloads.toString())
                        }
                    }


                }
            }

        }

        if (state.isLoading){
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center), color = Color.Green)
        }
        
        if (state.error.isNotBlank()){
            Text(text = state.error, modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center), textAlign = TextAlign.Center)
        }

    }


}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen(navController = rememberNavController())
}