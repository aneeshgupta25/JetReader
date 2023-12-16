package com.example.jetreader.screens.home

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.jetreader.R
import com.example.jetreader.components.BookCard
import com.example.jetreader.components.ProgressBar
import com.example.jetreader.components.SearchBar
import com.example.jetreader.components.SearchBookCard
import com.example.jetreader.data.GenreData
import com.example.jetreader.model.volume.Book
import com.example.jetreader.navigation.ReaderScreens
import com.example.jetreader.utils.AppConstants

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
//@Preview
@Composable
fun HomeScreen(
    searchViewModel: SearchViewModel,
    homeScreenViewModel: HomeScreenViewModel,
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
                if (!it) HomeContent(searchViewModel, homeScreenViewModel, navController)
                else SearchResults(
                    searchViewModel = searchViewModel,
                    homeScreenViewModel = homeScreenViewModel,
                    navController = navController
                )
            }
        }
    }
    BackHandler(
        enabled = searchViewModel.searchBarVisible
    ) {
        searchViewModel.hideSearchContents()
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeContent(
    searchViewModel: SearchViewModel,
    homeScreenViewModel: HomeScreenViewModel,
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp)
            .verticalScroll(state = rememberScrollState(), enabled = true)
    ) {
        Categories(categoryTitle = "", isCategory = false, navController = navController) {
            if (searchViewModel.defaultList.isNullOrEmpty())
                ProgressBar()
            else
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(searchViewModel.defaultList!!.size) {
                        BookCard(
                            modifier = Modifier
                                .height(
                                    AppConstants
                                        .getScreenHeightInDp()
                                        .times(0.4f)
                                )
                                .width(
                                    AppConstants
                                        .getScreenWidthInDp()
                                        .times(0.38f)
                                ),
                            textSize = 15.sp,
                            authorSize = 12.sp,
                            ratingSize = 10.sp,
                            book = searchViewModel.defaultList!![it],
                            fireStoreId = isBookInCart(
                                homeScreenViewModel,
                                searchViewModel.defaultList!![it]
                            ),
                            navController = navController
                        )
                    }
                }
        }
        Categories(
            categoryTitle = "Explore by Genre",
            category = "Genre",
            cornerRadius = 10.dp,
            navController = navController,
            showForwardIcon = false
        ) {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                val list = GenreData.getGenreData()
                items(count = list.size) {
                    Card(
                        modifier = Modifier
                            .width(
                                AppConstants
                                    .getScreenWidthInDp()
                                    .times(0.5f)
                            )
                            .height(
                                AppConstants
                                    .getScreenHeightInDp()
                                    .times(0.14f)
                            ),
                        shape = RoundedCornerShape(corner = CornerSize(10.dp)),
                        onClick = {
                            navController.navigate(ReaderScreens.CategoriesScreen.name + "/${list[it].categoryType}")
                        }
                    ) {
                        Box(modifier = Modifier.fillMaxSize()) {
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
        }
        Categories(
            categoryTitle = "On Your Wishlist",
            category = "WishList",
            showForwardIcon = true,
            navController = navController
        ) {
            if (homeScreenViewModel.booksFromCart.value.e != null)
                Text(text = "Something went wrong!!")
            else if (homeScreenViewModel.booksFromCart.value.data.isNullOrEmpty())
                ProgressBar()
            else {
                var list = homeScreenViewModel.booksFromCart.value.data
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(list!!.size) {
                        BookCard(
                            modifier = Modifier
                                .height(
                                    AppConstants
                                        .getScreenHeightInDp()
                                        .times(0.4f)
                                )
                                .width(
                                    AppConstants
                                        .getScreenWidthInDp()
                                        .times(0.38f)
                                ),
                            textSize = 15.sp,
                            authorSize = 12.sp,
                            ratingSize = 10.sp,
                            book = list[it].book,
                            fireStoreId = isBookInCart(homeScreenViewModel, list[it].book!!),
                            navController = navController
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Categories(
    isCategory: Boolean = true,
    category: String = "Explore by Genre",
    categoryTitle: String,
    showForwardIcon: Boolean = true,
    cornerRadius: Dp = 10.dp,
    navController: NavController,
    content: @Composable () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center
    ) {
        if (isCategory) Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = category, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            if (showForwardIcon)
                IconButton(onClick = {

                    navController.navigate(ReaderScreens.CategoriesScreen.name + "/${category}")
                }) {
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
        Spacer(modifier = Modifier.height(10.dp))
    }
}

@Composable
fun SearchResults(
    searchViewModel: SearchViewModel,
    homeScreenViewModel: HomeScreenViewModel,
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
                    columns = GridCells.Fixed(1),
                    contentPadding = PaddingValues(15.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(count = list!!.size) {
                        SearchBookCard(
                            book = list[it],
                            fireStoreId = isBookInCart(homeScreenViewModel, list[it]),
                            navController = navController
                        )
                    }
                }
            }
        }
    }
}

fun isBookInCart(
    homeScreenViewModel: HomeScreenViewModel,
    book: Book
): String? {
    val bookFound =
        homeScreenViewModel.booksFromCart.value.data?.find { book.id.equals(it.book?.id) }
    return bookFound?.id
}