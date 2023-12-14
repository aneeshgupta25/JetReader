package com.example.jetreader.screens.home

import android.annotation.SuppressLint
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.currentCompositionLocalContext
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.jetreader.R
import com.example.jetreader.components.BookCard
import com.example.jetreader.components.InputViewModel
import com.example.jetreader.components.ProgressBar
import com.example.jetreader.components.SearchBar
import com.example.jetreader.data.GenreData
import com.example.jetreader.model.volume.Book
import com.example.jetreader.repository.ReaderRepository
import com.example.jetreader.utils.AppConstants

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
//@Preview
@Composable
fun HomeScreen(
    searchViewModel: SearchViewModel,
    navController: NavController
) {
    Scaffold(topBar = {
        Crossfade(targetState = searchViewModel.searchBarVisible, label = "Home topbar") {
            if (!it) HomeTopBar(searchViewModel)
            else SearchBar(searchViewModel = searchViewModel)
        }
    }) {
        Box(modifier = Modifier.padding(it)) {
            Crossfade(
                targetState = searchViewModel.searchContentVisible,
                label = "HomeScreen Content"
            ) {
                if (!it) HomeContent(searchViewModel, navController)
                else SearchResults(searchViewModel, navController = navController)
            }
        }
    }
}

@Composable
fun HomeTopBar(
    searchViewModel: SearchViewModel,
) {
    Row(
        modifier = Modifier
            .padding(vertical = 10.dp)
            .fillMaxHeight(0.075f)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(0.8f)
        ) {
            Image(
                modifier = Modifier.fillMaxHeight(0.8f),
                painter = painterResource(id = R.drawable.llogo),
                contentDescription = "logo"
            )
            Text(text = "JetReader", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }
        IconButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = { searchViewModel.toggleSearchBarVisibility() },
        ) {
            Icon(
                modifier = Modifier.fillMaxSize(0.6f),
                painter = painterResource(id = R.drawable.search),
                contentDescription = "Search",
            )
        }
    }
}

@Composable
fun HomeContent(
    searchViewModel: SearchViewModel,
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp)
            .verticalScroll(state = rememberScrollState(), enabled = true)
    ) {
        Categories(isCategory = false) {
            if (searchViewModel.defaultList.isNullOrEmpty()) {
                ProgressBar()
            } else
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(searchViewModel.defaultList!!.size) {
                        BookCard(
                            modifier = Modifier
                                .height(AppConstants.getScreenHeightInDp() / 3)
                                .width(AppConstants.getScreenWidthInDp() / 3),
                            textSize = 15.sp,
                            authorSize = 12.sp,
                            ratingSize = 10.sp,
                            book = searchViewModel.defaultList!![it],
                            navController = navController
                        )
                    }
                }
        }
        Categories(
            category = "Explore by Genre", cornerRadius = 10.dp
        ) {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                val list = GenreData.getGenreData()
                items(count = list.size) {
                    Box(
                        modifier = Modifier
                            .width(AppConstants.getScreenWidthInDp() / 2)
                            .height(AppConstants.getScreenHeightInDp() / 8)
                            .clip(RoundedCornerShape(corner = CornerSize(10.dp)))
                    ) {
                        Image(
                            painter = painterResource(id = list[it].imageId),
                            contentDescription = "genre",
                            contentScale = ContentScale.FillBounds,
                        )
                        Text(
                            text = list[it].categoryType.uppercase(),
                            color = Color.White,
                            modifier = Modifier
                                .align(Alignment.BottomStart)
                                .background(Color.Transparent)
                                .padding(vertical = 10.dp, horizontal = 10.dp),
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
        Categories(
            category = "On Your Wishlist"
        ) {
            if (searchViewModel.defaultList.isNullOrEmpty()) {
                ProgressBar()
            } else
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(searchViewModel.defaultList!!.size) {
                        BookCard(
                            modifier = Modifier
                                .height(AppConstants.getScreenHeightInDp() / 3)
                                .width(AppConstants.getScreenWidthInDp() / 3),
                            textSize = 15.sp,
                            authorSize = 12.sp,
                            ratingSize = 10.sp,
                            book = searchViewModel.defaultList!![it],
                            navController = navController
                        )
                    }
                }
        }
    }
}

@Composable
fun Categories(
    isCategory: Boolean = true,
    category: String = "Explore by Genre",
    cornerRadius: Dp = 10.dp,
    content: @Composable () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        if (isCategory) Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = category, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Filled.ArrowForward,
                    contentDescription = "Go to $category",
                    tint = AppConstants.DarkYellow
                )
            }
        }
        Box(modifier = Modifier.clip(RoundedCornerShape(corner = CornerSize(cornerRadius)))) {
            content()
        }
        Spacer(modifier = Modifier.height(15.dp))
    }
}

@Composable
fun SearchResults(
    searchViewModel: SearchViewModel,
    navController: NavController
) {
    Column {
        Divider(thickness = 1.dp, color = Color.LightGray)
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            if (searchViewModel.searchLoading) {
                ProgressBar()
            } else if (searchViewModel.searchResult.isNullOrEmpty()) {
                Text(
                    text = "No Books found for entered keywords..",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            } else {
                val list = searchViewModel.searchResult
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(vertical = 15.dp, horizontal = 10.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(count = list!!.size) {
                        BookCard(
                            modifier = Modifier
                                .height(AppConstants.getScreenHeightInDp() / 3)
                                .width(AppConstants.getScreenWidthInDp() / 3),
                            textSize = 12.sp,
                            ratingSize = 10.sp,
                            book = list[it],
                            navController = navController
                        )
                    }
                }
            }
        }
    }
}