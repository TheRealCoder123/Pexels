package com.upnext.pexels.presentation.main_navigation.AddSocialScreen

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.upnext.pexels.R
import com.upnext.pexels.common.SocialUserPreferences
import com.upnext.pexels.presentation.components.GrayBgTextField
import com.upnext.pexels.presentation.components.GreenButton
import com.upnext.pexels.presentation.main_navigation.profile.ProfileViewModel
import kotlinx.coroutines.launch

@Composable
fun AddSocialsScreen(
    profileViewModel: ProfileViewModel = hiltViewModel(),
    addSocialsViewModel: AddSocialsViewModel = hiltViewModel(),
    onBackPressed: () -> Unit
) {


    val whatSocialPref = addSocialsViewModel.whatSocialState.value
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    val state = addSocialsViewModel.updateState.value

    LaunchedEffect(key1 = true){
        when(whatSocialPref){
            SocialUserPreferences.INSTAGRAM -> {
                val userInstagram = profileViewModel.state.value.user?.instagram
                addSocialsViewModel.textState.value = userInstagram.toString()
            }
            SocialUserPreferences.TWITTER -> {
                val userTwitter = profileViewModel.state.value.user?.twitter
                addSocialsViewModel.textState.value = userTwitter.toString()
            }
            SocialUserPreferences.WEBSITE -> {
                val userWebsite = profileViewModel.state.value.user?.website
                addSocialsViewModel.textState.value = userWebsite.toString()
            }
        }
    }

    LaunchedEffect(key1 = state.error){
        if (state.error.isNotBlank()){
            Toast.makeText(context, state.error, Toast.LENGTH_SHORT).show()
        }
    }

    LaunchedEffect(key1 = state.done){
        if (state.done){
            Toast.makeText(context, R.string.successfully_updated, Toast.LENGTH_SHORT).show()
        }
    }

    if (state.loading){
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
            CircularProgressIndicator(color = Color.Green)
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
    ) {

        item {


            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
                IconButton(onClick = {
                    onBackPressed()
                }) {
                    Icon(
                        Icons.Default.ArrowBack,
                        contentDescription = stringResource(id = R.string.back),
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
                        append(" ${stringResource(id = R.string.profile)} ")
                    }
                    withStyle(
                        style = SpanStyle(
                            color = Color.Black
                        )
                    ){
                        append(
                            when(whatSocialPref){
                            SocialUserPreferences.INSTAGRAM -> stringResource(id = R.string.instagram)
                            SocialUserPreferences.TWITTER -> stringResource(id = R.string.twitter)
                            SocialUserPreferences.WEBSITE -> stringResource(id = R.string.website)
                        })
                    }
                },
                fontSize = 25.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            )

            Spacer(modifier = Modifier.height(25.dp))

            GrayBgTextField(
                value = addSocialsViewModel.textState.value,
                onValueChange = { addSocialsViewModel.textState.value = it },
                label = when(whatSocialPref){
                    SocialUserPreferences.INSTAGRAM -> stringResource(R.string.instagram_username)
                    SocialUserPreferences.TWITTER -> stringResource(R.string.twitter_username)
                    SocialUserPreferences.WEBSITE -> stringResource(R.string.website_url)
                },
                inputType = KeyboardType.Text
            )

            Spacer(modifier = Modifier.height(25.dp))


            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp)
            ){
                GreenButton(
                    text = stringResource(id = R.string.confirm_changes)
                ) {
                    scope.launch {
                        val userHashMap = HashMap<String, Any>()

                        when(whatSocialPref){
                            SocialUserPreferences.INSTAGRAM -> {
                                userHashMap["instagram"] = if(addSocialsViewModel.textState.value
                                        .replace('@', ' ')
                                        .trim().isEmpty()
                                ){
                                    ""
                                }else if (addSocialsViewModel.textState.value.contains("@")){
                                    addSocialsViewModel.textState.value
                                }else{
                                    "@${addSocialsViewModel.textState.value}"
                                }
                            }
                            SocialUserPreferences.TWITTER -> {
                                userHashMap["twitter"] = if(addSocialsViewModel.textState.value
                                        .replace('@', ' ')
                                        .trim().isEmpty()
                                ){
                                    ""
                                }else if (addSocialsViewModel.textState.value.contains("@")){
                                    addSocialsViewModel.textState.value
                                }else{
                                    "@${addSocialsViewModel.textState.value}"
                                }
                            }
                            SocialUserPreferences.WEBSITE -> {
                                userHashMap["website"] = addSocialsViewModel.textState.value
                            }
                        }

                        addSocialsViewModel.updateUserData(userHashMap)

                    }
                }
            }

        }

    }


}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun AddSocialsScreenPreview() {
    AddSocialsScreen {

    }
}