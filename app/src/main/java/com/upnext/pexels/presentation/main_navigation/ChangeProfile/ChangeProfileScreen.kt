package com.upnext.pexels.presentation.main_navigation.ChangeProfile

import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.upnext.pexels.R
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
    val updateState = changeProfileViewModel.updateState.value
    val updateImageState = changeProfileViewModel.updateImageState.value
    val isImageChanged = changeProfileViewModel.isImageChanged
    val context = LocalContext.current

    val scope = rememberCoroutineScope()


    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument(),
        onResult = { uri ->
            changeProfileViewModel.imageUri.value = uri.toString()
            isImageChanged.value = true
        }
    )

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
                            contentDescription = stringResource(R.string.view),
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
                            append(stringResource(R.string.profile))
                        }
                        withStyle(
                            style = SpanStyle(
                                color = Color.Black
                            )
                        ){
                            append(stringResource(R.string.personal_info))
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
                        text = stringResource(R.string.avatar),
                        color = Color.Gray,
                        modifier = Modifier
                            .fillMaxWidth(),
                        textAlign = TextAlign.Start
                    )

                    Spacer(modifier = Modifier.height(15.dp))

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        CircleImageView(
                            image = changeProfileViewModel.imageUri.value.ifEmpty { R.drawable.no_profile_image },
                            size = 80.dp,
                            modifier = Modifier.clickable {
                                launcher.launch(arrayOf("image/*"))
                            }
                        )

                        Spacer(modifier = Modifier.width(10.dp))

                        if(updateImageState.loading){
                            CircularProgressIndicator(color = Color.Green)
                        }

                        if (isImageChanged.value){
                            IconButton(onClick = {
                                changeProfileViewModel.imageUri.value = user.image
                                isImageChanged.value = false
                            }) {
                                Icon(
                                    Icons.Default.Close,
                                    contentDescription = stringResource(R.string.remove),
                                    tint = Color.Gray
                                )
                            }

                            Spacer(modifier = Modifier.width(10.dp))

                            IconButton(onClick = {
                                scope.launch {
                                    if (changeProfileViewModel.imageUri.value.isNotBlank()){
                                        changeProfileViewModel.updateImage()
                                        isImageChanged.value = false
                                    }
                                }
                            }) {
                                Icon(
                                    Icons.Default.Done,
                                    contentDescription = stringResource(id = R.string.done),
                                    tint = Color.Green
                                )
                            }

                        }

                    }

                }

                Spacer(modifier = Modifier.height(30.dp))


                GrayBgTextField(
                    value = changeProfileViewModel.firstNameState.value,
                    onValueChange = { changeProfileViewModel.firstNameState.value = it },
                    label = stringResource(id = R.string.first_name),
                    inputType = KeyboardType.Text
                )

                Spacer(modifier = Modifier.height(10.dp))

                GrayBgTextField(
                    value = changeProfileViewModel.lastNameState.value,
                    onValueChange = { changeProfileViewModel.lastNameState.value = it },
                    label = stringResource(id = R.string.lastname),
                    inputType = KeyboardType.Text
                )

                Spacer(modifier = Modifier.height(10.dp))

                GrayBgTextField(
                    value = changeProfileViewModel.shortBioNameState.value,
                    onValueChange = { changeProfileViewModel.shortBioNameState.value = it },
                    label = stringResource(R.string.short_bio),
                    inputType = KeyboardType.Text
                )

                Spacer(modifier = Modifier.height(10.dp))

                GrayBgTextField(
                    value = changeProfileViewModel.tagState.value,
                    onValueChange = { changeProfileViewModel.tagState.value = it },
                    label = stringResource(R.string.tag),
                    inputType = KeyboardType.Text
                )

                Spacer(modifier = Modifier.height(10.dp))

                Box(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp)
                ){
                    GreenButton(
                        text = stringResource(R.string.confirm_changes)
                    ) {
                        scope.launch {

                            if (changeProfileViewModel.tagState.value.replace('@', ' ').trim().isEmpty()){
                                Toast.makeText(context, R.string.please_enter_your_tag, Toast.LENGTH_SHORT).show()
                                return@launch
                            }

                            val userHashMap = HashMap<String, Any>()

                            if(changeProfileViewModel.firstNameState.value != user.firstName){
                                userHashMap["firstName"] = changeProfileViewModel.firstNameState.value
                            }

                            if(changeProfileViewModel.lastNameState.value != user.lastname){
                                userHashMap["lastname"] = changeProfileViewModel.lastNameState.value
                            }

                            if(changeProfileViewModel.shortBioNameState.value != user.bio){
                                userHashMap["bio"] = changeProfileViewModel.shortBioNameState.value
                            }

                            if(changeProfileViewModel.tagState.value != user.tag){
                                userHashMap["tag"] = if (changeProfileViewModel.tagState.value.contains("@")){
                                    changeProfileViewModel.tagState.value
                                }else{
                                    "@${changeProfileViewModel.tagState.value}"
                                }
                            }

                            changeProfileViewModel.updateUserData(userHashMap)

                        }
                    }
                }

                LaunchedEffect(key1 = updateState.error.isNotBlank()){
                    if (updateState.error.isNotBlank()){
                        Toast.makeText(context, updateState.error, Toast.LENGTH_SHORT).show()
                    }
                }

                LaunchedEffect(key1 = updateState.done){
                    if(updateState.done){
                        Toast.makeText(context, R.string.profile_has_been_successfully_updated, Toast.LENGTH_SHORT).show()
                    }
                }

                LaunchedEffect(key1 = updateImageState.error.isNotBlank()){
                    if (updateImageState.error.isNotBlank()){
                        Toast.makeText(context, updateState.error, Toast.LENGTH_SHORT).show()
                    }
                }

                LaunchedEffect(key1 = updateImageState.done){
                    if(updateImageState.done){
                        Toast.makeText(context, R.string.profile_image_has_been_successfully_updated, Toast.LENGTH_SHORT).show()
                    }
                }

            }


        }

    }

    if(updateState.loading){
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
            CircularProgressIndicator(color = Color.Green)
        }
    }





}



@Preview(showSystemUi = true, showBackground = true)
@Composable
fun ChangeProfileScreenPreview() {
    ChangeProfileScreen()
}