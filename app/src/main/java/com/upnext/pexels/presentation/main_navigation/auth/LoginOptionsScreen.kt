package com.upnext.pexels.presentation.main_navigation.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.upnext.pexels.R

@Composable
fun LoginOptionsScreen(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize()){
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding()
        ){
            Image(
                modifier = Modifier
                    .fillMaxSize(),
                painter = painterResource(id = R.drawable.auth_bg),
                contentDescription = "Collage Image",
                contentScale = ContentScale.FillBounds,
            )
        }
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color(0xC3171717),
                            Color(0xD8171717),
                            Color.Black
                        ),
                        startY = 300f
                    )
                )
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(bottom = 75.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            contentPadding = PaddingValues(bottom = 80.dp)
        ){
            item {

                Image(
                    painter = painterResource(id = R.drawable.pexels_logo),
                    contentDescription = "Pexels Logo",
                    modifier = Modifier.size(200.dp)
                )

                Text(
                    text = "Thousands of amazing photos & videos. For Free.",
                    color = Color.White,
                    style = MaterialTheme.typography.headlineMedium,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(100.dp))

                Box(modifier = Modifier
                    .fillMaxWidth()
                    .padding(25.dp)){
                    Button(
                        modifier = Modifier
                            .height(50.dp)
                            .fillMaxWidth(),
                        onClick = {
                             navController.navigate(AuthScreens.RegisterWithEmailScreen.route)
                        },
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = Color.Black),
                    ) {
                        Icon(
                            imageVector = Icons.Default.Email,
                            contentDescription = "Continue with e-mail",
                            tint = Color.Gray,
                            modifier = Modifier.padding(end = 8.dp)
                        )
                        Text(
                            text = "Continue with e-mail",
                            color = Color.Black
                        )
                    }
                }

                Box(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 25.dp)){
                    Button(
                        modifier = Modifier
                            .height(50.dp)
                            .fillMaxWidth(),
                        onClick = {

                        },
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = Color.Black),
                    ) {
                        Icon(
                            imageVector = Icons.Default.AccountCircle,
                            contentDescription = "Continue with google",
                            tint = Color.Gray,
                            modifier = Modifier.padding(end = 8.dp)
                        )
                        Text(
                            text = "Continue with google",
                            color = Color.Black
                        )
                    }
                }
                
                Spacer(modifier = Modifier.height(20.dp))
                
                Text(
                    modifier = Modifier.fillMaxWidth()
                        .clickable {
                              navController.navigate(AuthScreens.LoginScreen.route)
                        },
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                color = Color.Gray,
                                fontSize = 18.sp
                            )
                        ){
                            append("Already got an account?")
                        }
                        withStyle(
                            style = SpanStyle(
                                color = Color.White,
                                fontSize = 18.sp
                            )
                        ){
                            append(" Sign In")
                        }
                    },
                    textAlign = TextAlign.Center
                )

            }

        }

    }
}

@Preview(showSystemUi = true)
@Composable
fun LoginOptionsScreenPreview() {
    LoginOptionsScreen(rememberNavController())
}