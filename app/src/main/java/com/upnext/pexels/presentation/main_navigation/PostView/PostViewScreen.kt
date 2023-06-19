package com.upnext.pexels.presentation.main_navigation.PostView

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun PostViewScreen(
    navController: NavHostController,
    postViewScreenViewModel: PostViewScreenViewModel = hiltViewModel(),
) {

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Text(text = "Post View Screen")
    }

}

@Composable
@Preview(showSystemUi = true, showBackground = true)
fun PostViewScreenPreview() {
    PostViewScreen(rememberNavController())
}