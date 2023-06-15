package com.upnext.pexels.presentation.main_navigation.settings

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.gson.Gson
import com.upnext.pexels.R
import com.upnext.pexels.common.Constants
import com.upnext.pexels.data.remote.Post
import com.upnext.pexels.presentation.components.BlueButton
import com.upnext.pexels.presentation.components.GrayBackgroundButton
import com.upnext.pexels.presentation.main_navigation.auth.AuthScreens
import com.upnext.pexels.presentation.main_navigation.profile.components.CircleImageView
import com.upnext.pexels.presentation.main_navigation.settings.components.SettingsNavView
import com.upnext.pexels.presentation.service.UploadPostService
import kotlinx.coroutines.launch

@Composable
fun SettingsScreen(
    settingsViewModel: SettingsViewModel = hiltViewModel(),
    navController: NavHostController
) {


    val state = settingsViewModel.state.value

    state.user?.let {user->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding(),
            contentPadding = PaddingValues(bottom = 100.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            item {

                Box(modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp)
                ){
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {


                        Text(text = user.tag, fontSize = 18.sp)

                        Box(modifier = Modifier.padding(5.dp)){
                            GrayBackgroundButton(text = "Done"){

                            }
                        }

                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .clickable {
                             navController.navigate(AuthScreens.PersonalInfoScreen.route)
                        },
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircleImageView(
                        user.image.ifBlank {
                            R.drawable.no_profile_image
                        }
                    )

                    Spacer(modifier = Modifier.height(15.dp))

                    Text(
                        text = "${user.firstName} ${user.lastname}",
                        fontSize = 18.sp
                    )

                }

                Spacer(modifier = Modifier.height(10.dp))

                Box(modifier = Modifier.padding(horizontal = 15.dp, vertical = 8.dp)){
                    SettingsNavView(
                        title = "Email",
                        value = user.email,
                        background = Color.Transparent
                    ) {

                    }
                }



                Box(modifier = Modifier.padding(horizontal = 15.dp, vertical = 8.dp)){
                    SettingsNavView(
                        title = "Instagram",
                        value = user.instagram,
                        background = Color.Transparent
                    ) {

                    }
                }

                Box(modifier = Modifier.padding(horizontal = 15.dp, vertical = 8.dp)){
                    SettingsNavView(
                        title = "Twitter",
                        value = user.twitter,
                        background = Color.Transparent
                    ) {

                    }
                }


                Box(modifier = Modifier.padding(horizontal = 15.dp, vertical = 8.dp)){
                    SettingsNavView(
                        title = "Website",
                        value = user.website,
                        background = Color.Transparent
                    ) {

                    }
                }

                Box(modifier = Modifier.padding(horizontal = 15.dp, vertical = 8.dp)){
                    SettingsNavView(
                        title = "Tag",
                        value = user.tag,
                        background = Color.Transparent
                    ) {

                    }
                }


                Box(modifier = Modifier.padding(horizontal = 15.dp, vertical = 8.dp)){
                    SettingsNavView(
                        title = "Language",
                        value = "Us English",
                        background = Color.Transparent
                    ) {

                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "Logout",
                    color = Color.Blue,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                        .clickable {
                            settingsViewModel.logOut()
                            navController.navigate(AuthScreens.LoginOptionsScreen.route) {
                                popUpTo(navController.graph.startDestinationRoute!!) {
                                    saveState = false
                                    inclusive = true
                                }
                            }
                        },
                    textAlign = TextAlign.Center
                )


            }


        }
    }





}

@Composable
@Preview(
    showBackground = true,
    showSystemUi = true
)
fun SettingsScreenPreview() {
    SettingsScreen(navController = rememberNavController())
}