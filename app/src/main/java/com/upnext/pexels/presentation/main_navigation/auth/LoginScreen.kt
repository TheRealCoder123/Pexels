package com.upnext.pexels.presentation.main_navigation.auth

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.upnext.pexels.data.remote.LoginParams
import com.upnext.pexels.presentation.components.GreenButton
import com.upnext.pexels.presentation.main_navigation.auth.components.CustomTextField
import com.upnext.pexels.presentation.main_navigation.auth.view_models.LoginViewModel
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    navController: NavController
) {

    val context = LocalContext.current
    val state = viewModel.state.value
    val scope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .background(Color.Black)
    ){
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            item {

                TopAppBar(
                    backgroundColor = Color.Black,
                    title = { Text("") },
                    navigationIcon = {
                        IconButton(onClick = {
                            navController.navigateUp()
                        }) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = "back",
                                tint = Color.White
                            )
                        }
                    }
                )


                Box(modifier = Modifier.padding(10.dp)) {
                    Text(
                        text = "Log In",
                        color = Color.White,
                        fontSize = 40.sp
                    )
                }

                Box(modifier = Modifier.padding(20.dp)) {
                    Text(
                        text = "Welcome back! Enter your details bellow",
                        color = Color.DarkGray,
                        fontSize = 19.sp,
                        textAlign = TextAlign.Center
                    )
                }

                CustomTextField(
                    value = viewModel.emailTextFieldState.value,
                    onValueChange = {
                        viewModel.emailTextFieldState.value = it
                    },
                    label = "Email",
                    inputType = KeyboardType.Email
                )

                Spacer(modifier = Modifier.height(10.dp))

                CustomTextField(
                    value = viewModel.passwordTextFieldState.value,
                    onValueChange = {
                        viewModel.passwordTextFieldState.value = it
                    },
                    label = "Password",
                    inputType = KeyboardType.Password
                )

                Spacer(modifier = Modifier.height(20.dp))



                Box(modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp)
                ){
                    GreenButton(text = "Log In") {
                        scope.launch {
                            viewModel.login(
                                LoginParams(
                                    viewModel.emailTextFieldState.value,
                                    viewModel.passwordTextFieldState.value
                                )
                            )
                        }
                    }
                }

                LaunchedEffect(key1 = state.isLoggedIn){
                    scope.launch {
                        if (state.isLoggedIn){
                            Toast.makeText(
                                context,
                                "Successfully logged in! Enjoy",
                                Toast.LENGTH_SHORT).show()
                            navController.navigate(AuthScreens.ProfileScreen.route) {
                                popUpTo(navController.graph.startDestinationRoute!!) {
                                    saveState = false
                                    inclusive = true
                                }
                            }
                        }
                    }
                }

                LaunchedEffect(key1 = state.error){
                    if (state.error.isNotBlank()) {
                        val error = state.error
                        Toast.makeText(context, "$error", Toast.LENGTH_LONG).show()
                    }
                }

            }
        }

        if (state.isLoading) {
            CircularProgressIndicator(color = Color.Red, modifier = Modifier.align(Alignment.Center))
        }

    }


}

@Composable
@Preview(showSystemUi = true, showBackground = true)
fun LoginScreenPreview() {
    LoginScreen(navController = rememberNavController())
}