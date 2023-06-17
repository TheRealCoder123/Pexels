package com.upnext.pexels.presentation.main_navigation.auth

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material.TextField
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.upnext.pexels.R
import com.upnext.pexels.data.remote.RegisterParams
import com.upnext.pexels.presentation.components.GreenButton
import com.upnext.pexels.presentation.main_navigation.auth.components.CustomTextField
import com.upnext.pexels.presentation.main_navigation.auth.view_models.RegisterWithEmailViewModel
import kotlinx.coroutines.launch

@Composable
fun RegisterWithEmailScreen(
    navController: NavController,
    viewModel: RegisterWithEmailViewModel = hiltViewModel(),
) {

    val scope = rememberCoroutineScope()
    val state = viewModel.state.value
    val context = LocalContext.current


    Box(
        modifier = Modifier
        .fillMaxSize()
        .systemBarsPadding()
        .background(Color.Black)
    ) {

        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            contentPadding = PaddingValues(bottom = 80.dp)
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
                                contentDescription = stringResource(R.string.back),
                                tint = Color.White
                            )
                        }
                    }
                )



                Box(modifier = Modifier.padding(10.dp)) {
                    Text(
                        text = stringResource(R.string.join_pexels),
                        color = Color.White,
                        fontSize = 40.sp
                    )
                }

                Box(modifier = Modifier.padding(20.dp)) {
                    Text(
                        text = stringResource(R.string.join_a_huge_community_of_amazing_photographers_from_all_around_the_world),
                        color = Color.DarkGray,
                        fontSize = 19.sp,
                        textAlign = TextAlign.Center
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                CustomTextField(
                    value = viewModel.firstNameTextFieldState.value,
                    onValueChange = {
                        viewModel.firstNameTextFieldState.value = it
                    },
                    label = stringResource(R.string.first_name),
                    inputType = KeyboardType.Text
                )

                Spacer(modifier = Modifier.height(10.dp))

                CustomTextField(
                    value = viewModel.lastNameTextFieldState.value,
                    onValueChange = {
                        viewModel.lastNameTextFieldState.value = it
                    },
                    label = stringResource(R.string.lastname),
                    inputType = KeyboardType.Text
                )

                Spacer(modifier = Modifier.height(10.dp))

                CustomTextField(
                    value = viewModel.emailTextFieldState.value,
                    onValueChange = {
                        viewModel.emailTextFieldState.value = it
                    },
                    label = stringResource(R.string.email),
                    inputType = KeyboardType.Email
                )

                Spacer(modifier = Modifier.height(10.dp))

                CustomTextField(
                    value = viewModel.passwordTextFieldState.value,
                    onValueChange = {
                        viewModel.passwordTextFieldState.value = it
                    },
                    label = stringResource(R.string.password),
                    inputType = KeyboardType.Password
                )

                Spacer(modifier = Modifier.height(25.dp))

                Box(modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp)){
                    GreenButton(text = stringResource(R.string.create_an_account)) {
                        scope.launch {
                            val registerParams = RegisterParams(
                                viewModel.firstNameTextFieldState.value,
                                viewModel.lastNameTextFieldState.value,
                                viewModel.emailTextFieldState.value,
                                viewModel.passwordTextFieldState.value
                            )
                            viewModel.register(registerParams = registerParams)
                        }
                    }
                }

                LaunchedEffect(key1 = state.accountCreated){
                    scope.launch {
                        if (state.accountCreated){
                            Toast.makeText(
                                context,
                                R.string.account_created_now_you_can_log_in,
                                Toast.LENGTH_SHORT).show()
                            navController.navigateUp()
                        }
                    }
                }

                LaunchedEffect(key1 = state.error.isNotBlank()){
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

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun LoginWithEmailScreenPreview() {
    RegisterWithEmailScreen(rememberNavController())
}