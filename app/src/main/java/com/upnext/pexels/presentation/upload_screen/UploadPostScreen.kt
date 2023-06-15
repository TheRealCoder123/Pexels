package com.upnext.pexels.presentation.upload_screen

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.gson.Gson
import com.upnext.pexels.R
import com.upnext.pexels.common.Constants.INTENT_PARAM_POSTS_TO_SERVICE
import com.upnext.pexels.common.getCategories
import com.upnext.pexels.data.remote.Post
import com.upnext.pexels.presentation.components.BlueButton
import com.upnext.pexels.presentation.components.CategoryView
import com.upnext.pexels.presentation.components.GrayBgTextField
import com.upnext.pexels.presentation.components.GreenButton
import com.upnext.pexels.presentation.components.WhiteGrayOutlinedButton
import com.upnext.pexels.presentation.main_navigation.Screen
import com.upnext.pexels.presentation.main_navigation.auth.components.CustomTextField
import com.upnext.pexels.presentation.service.UploadPostService
import com.upnext.pexels.presentation.upload_screen.components.RoundedCornerImage
import com.upnext.pexels.presentation.upload_screen.components.ShowImageDialog
import com.upnext.pexels.presentation.upload_screen.other.Media
import kotlinx.coroutines.launch

@Composable
fun UploadPostScreen(
    navController: NavController,
    viewModel: UploadPostViewModel = hiltViewModel(),
) {


    val context = LocalContext.current

    val currentSelectUriId = viewModel.selectedMedia.indexOf(viewModel.currentSelectItem.value)

    val scope = rememberCoroutineScope()


    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenMultipleDocuments(),
        onResult = { uris ->
            viewModel.selectedMedia.addAll(uris)
            if (viewModel.selectedMedia.isNotEmpty() && viewModel.currentSelectItem.value == null){
                viewModel.currentSelectItem.value = viewModel.selectedMedia[0]
            }
        }
    )

    if(viewModel.selectedMedia.isNotEmpty()){

        LazyColumn(
            modifier = Modifier
                .systemBarsPadding()
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            contentPadding = PaddingValues(bottom = 80.dp)
        ){
            item {
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                ){
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        IconButton(onClick = {
                            navController.navigateUp()
                        }) {
                            Icon(
                                Icons.Default.ArrowBack,
                                contentDescription = "Back",
                                tint = Color.Black
                            )
                        }

                        Row {

                            Box(modifier = Modifier.padding(5.dp)){
                                BlueButton(text = "Select More"){
                                    launcher.launch(arrayOf("image/*", "video/*"))
                                }
                            }

                            Box(modifier = Modifier.padding(5.dp)){
                                BlueButton(text = "Upload"){
                                    scope.launch {
                                        if(viewModel.selectedCategories.isEmpty()) {
                                            Toast.makeText(
                                                context,
                                                "Please select at least one category",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                            return@launch
                                        }
                                        val posts = viewModel.selectedMedia.map {
                                            Post(
                                                "",
                                                it.toString(),
                                                viewModel.tagsTextFieldState.value.split(", "),
                                                viewModel.locationTextFieldState.value,
                                                0,
                                                0,
                                                viewModel.selectedCategories
                                            )
                                        }
                                        val intent = Intent(context, UploadPostService::class.java)
                                        intent.putExtra(INTENT_PARAM_POSTS_TO_SERVICE, Gson().toJson(posts))
                                        context.startService(intent)
                                        navController.navigateUp()
                                    }
                                }
                            }
                        }

                    }
                }

                Spacer(modifier = Modifier.height(10.dp))


                Text(
                    text = "${currentSelectUriId+1} of ${viewModel.selectedMedia.size}",
                    fontSize = 20.sp,
                    color = Color.Black,
                )

                Spacer(modifier = Modifier.height(10.dp))

                RoundedCornerImage(
                    image = viewModel.currentSelectItem.value ?: "",
                    size = 280.dp,
                    isVideo = Media.isVideoUri(context = context, uri = viewModel.currentSelectItem.value!!),
                    modifier = Modifier.clickable {

                    },
                    showIcons = true
                )

                Spacer(modifier = Modifier.height(10.dp))

                WhiteGrayOutlinedButton(text = "Delete this media") {
                    viewModel.selectedMedia.remove(viewModel.currentSelectItem.value)
                    if (viewModel.selectedMedia.isNotEmpty()){
                        viewModel.currentSelectItem.value = viewModel.selectedMedia[0]
                    }else{
                        Toast.makeText(context, "No media to upload, please select media for upload", Toast.LENGTH_SHORT).show()
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                LazyRow {
                    items(viewModel.selectedMedia){
                        Box(modifier = Modifier.padding(10.dp)){
                            RoundedCornerImage(
                                image = it,
                                size = 100.dp,
                                isVideo = Media.isVideoUri(context = context, uri = it),
                                modifier = if(viewModel.currentSelectItem.value == it){
                                    Modifier.border(3.dp, Color.DarkGray, RoundedCornerShape(20.dp))
                                }else{
                                    Modifier
                                }.clickable {
                                    viewModel.currentSelectItem.value = it
                                },
                                showIcons = true
                            )
                        }
                    }
                }

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 25.dp),
                    text = "Information",
                    textAlign = TextAlign.Start,
                    fontSize = 25.sp
                )

                Spacer(modifier = Modifier.height(20.dp))

                GrayBgTextField(
                    value = viewModel.locationTextFieldState.value,
                    onValueChange = { viewModel.locationTextFieldState.value = it },
                    label = "Location (Optional)",
                    inputType = KeyboardType.Text
                )

                Spacer(modifier = Modifier.height(20.dp))

                GrayBgTextField(
                    value = viewModel.tagsTextFieldState.value,
                    onValueChange = { viewModel.tagsTextFieldState.value = it },
                    label = "Tags (Optional)",
                    inputType = KeyboardType.Text
                )

                Box(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp)){
                    Text(
                        text = "Separate the tags by comma ex. nature, artic nature",
                        color = Color.Gray
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 25.dp),
                    text = "Categories",
                    textAlign = TextAlign.Start,
                    fontSize = 25.sp
                )

                Spacer(modifier = Modifier.height(20.dp))

                LazyRow {
                    items(getCategories()){category->
                        Box(modifier = Modifier.padding(10.dp)){
                            CategoryView(
                                category = category,
                                modifier = if(viewModel.selectedCategories.contains(category)){
                                    Modifier.border(3.dp, Color.DarkGray, RoundedCornerShape(10.dp))
                                }else{
                                    Modifier
                                }
                            ){
                                if(!viewModel.selectedCategories.contains(category)){
                                    viewModel.selectedCategories.add(category)
                                }else{
                                    viewModel.selectedCategories.remove(category)
                                }
                            }
                        }
                    }
                }

            }



        }

    }else{
        viewModel.selectedCategories.clear()
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            item {

                Text(
                    text = "Upload Your Content",
                    fontSize = 25.sp,
                    color = Color.Black,
                )

                Spacer(modifier = Modifier.height(20.dp))


                Text(
                    text = "Join hundreds of creators all around the world.",
                    fontSize = 15.sp,
                    color = Color.DarkGray,
                )

                Spacer(modifier = Modifier.height(20.dp))

                Box(modifier = Modifier.padding(horizontal = 50.dp, vertical = 10.dp)){
                    GreenButton(text = "Select media") {
                        launcher.launch(arrayOf("image/*", "video/*"))
                    }
                }

            }


        }
    }


    
    
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun UploadPostScreenPreview() {
    UploadPostScreen(navController = rememberNavController())
}