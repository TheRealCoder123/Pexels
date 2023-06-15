package com.upnext.pexels.presentation.main_navigation.ChangeProfile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.upnext.pexels.R
import com.upnext.pexels.data.remote.User
import com.upnext.pexels.presentation.components.GrayBgTextField
import com.upnext.pexels.presentation.components.GreenButton
import com.upnext.pexels.presentation.main_navigation.profile.components.CircleImageView
import kotlinx.coroutines.launch

@Composable
fun ChangeProfileScreen(
    changeProfileViewModel: ChangeProfileViewModel = hiltViewModel(),
    onBackPressed: () -> Unit = {}
) {

    val state = changeProfileViewModel.state.value
    val scope = rememberCoroutineScope()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
    ){

        item {

            state.user?.let {user->
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
                    IconButton(onClick = {
                        onBackPressed()
                    }) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = "View",
                            tint = Color.Gray
                        )
                    }
                }

                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                color = Color.Gray
                            )
                        ){
                            append("Profile >")
                        }
                        withStyle(
                            style = SpanStyle(
                                color = Color.Black
                            )
                        ){
                            append(" Personal Info")
                        }
                    },
                    fontSize = 25.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                )

                Spacer(modifier = Modifier.height(25.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 15.dp)
                ) {
                    Text(
                        text = "Avatar",
                        color = Color.Gray,
                        modifier = Modifier
                            .fillMaxWidth(),
                        textAlign = TextAlign.Start
                    )

                    Spacer(modifier = Modifier.height(15.dp))

                    CircleImageView(image = user.image.ifEmpty { R.drawable.no_profile_image }, size = 80.dp)
                }

                Spacer(modifier = Modifier.height(30.dp))


                GrayBgTextField(
                    value = changeProfileViewModel.firstNameState.value,
                    onValueChange = { changeProfileViewModel.firstNameState.value = it },
                    label = "First Name",
                    inputType = KeyboardType.Text
                )

                Spacer(modifier = Modifier.height(10.dp))

                GrayBgTextField(
                    value = changeProfileViewModel.lastNameState.value,
                    onValueChange = { changeProfileViewModel.lastNameState.value = it },
                    label = "Last Name",
                    inputType = KeyboardType.Text
                )

                Spacer(modifier = Modifier.height(10.dp))

                GrayBgTextField(
                    value = changeProfileViewModel.shortBioNameState.value,
                    onValueChange = { changeProfileViewModel.shortBioNameState.value = it },
                    label = "Short Bio",
                    inputType = KeyboardType.Text
                )

                Spacer(modifier = Modifier.height(10.dp))

                Box(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp)
                ){
                    GreenButton(
                        text = "Confirm Changes"
                    ) {
                        scope.launch {

                        }
                    }
                }

            }


        }

    }



}



@Preview(showSystemUi = true, showBackground = true)
@Composable
fun ChangeProfileScreenPreview() {
    ChangeProfileScreen()
}