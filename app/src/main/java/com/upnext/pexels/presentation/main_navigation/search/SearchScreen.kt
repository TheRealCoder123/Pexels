package com.upnext.pexels.presentation.main_navigation.search

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.upnext.pexels.R
import com.upnext.pexels.common.getCategories
import com.upnext.pexels.domain.model.Section
import com.upnext.pexels.presentation.components.CategoryView
import com.upnext.pexels.presentation.components.SearchButton
import com.upnext.pexels.presentation.main_navigation.profile.components.StatsView
import com.upnext.pexels.presentation.main_navigation.search.components.PostSearchView
import com.upnext.pexels.presentation.upload_screen.components.RoundedCornerImage

@Composable
fun SearchScreen(
    searchViewModel: SearchScreenViewModel = hiltViewModel(),
    onPostClicked: (String) -> Unit = {}
) {

    val state = searchViewModel.state.value

    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(bottom = 100.dp)
    ){

        item {
            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp, top = 50.dp, bottom = 10.dp)){
                SearchButton(
                    text = "Search for amazing content",
                ) {

                }
            }
        }

        item {
            Text(
                text = "Browse by categories",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp),
                textAlign = TextAlign.Start,
                fontSize = 25.sp
            )
            Divider(modifier = Modifier.fillMaxWidth().height(1.dp), color = Color.Gray)
            LazyRow {
                items(getCategories()){ category->
                    Box(modifier = Modifier.padding(10.dp)){
                        CategoryView(
                            category = category,
                        ){

                        }
                    }
                }
            }
        }

        if(state.sections.isNotEmpty()){
            items(state.sections){section: Section ->
               if (section.data.isNotEmpty()){
                   Text(
                       text = section.title,
                       modifier = Modifier
                           .fillMaxWidth()
                           .padding(15.dp),
                       textAlign = TextAlign.Start,
                       fontSize = 25.sp
                   )
                   Divider(modifier = Modifier.fillMaxWidth().height(1.dp), color = Color.Gray)
                   LazyRow {
                       items(section.data){
                           Box(modifier = Modifier.padding(10.dp)){
                               PostSearchView(post = it, onPostClick = {
                                   onPostClicked(it.postId)
                               })
                           }

                       }
                   }
               }
            }
        }



    }

    if (state.isLoading){
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
            CircularProgressIndicator(color = Color.Green)
        }
    }

}

@Composable
@Preview(showSystemUi = true, showBackground = true)
fun SearchScreenPreview() {
    SearchScreen()
}