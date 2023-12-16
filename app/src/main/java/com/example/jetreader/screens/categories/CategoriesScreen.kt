package com.example.jetreader.screens.categories

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.jetreader.components.CustomTopBar
import com.example.jetreader.components.ProgressBar
import com.example.jetreader.components.SearchBookCard
import com.example.jetreader.data.DataOrException
import com.example.jetreader.data.GenreData
import com.example.jetreader.model.MBook
import com.example.jetreader.model.genre.Genre
import com.example.jetreader.model.volume.Book
import com.example.jetreader.screens.home.HomeScreenViewModel
import com.example.jetreader.screens.home.isBookInCart
import com.example.jetreader.utils.AppConstants

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoriesScreen(
    dataType: String,
    homeScreenViewModel: HomeScreenViewModel,
    categoriesViewModel: CategoriesViewModel,
    navController: NavController
) {
    CategoriesContent(
        dataType = dataType,
        homeScreenViewModel = homeScreenViewModel,
        categoriesViewModel = categoriesViewModel,
        navController = navController,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoriesContent(
    dataType: String,
    homeScreenViewModel: HomeScreenViewModel,
    categoriesViewModel: CategoriesViewModel,
    navController: NavController,
) {
    Scaffold(
        topBar = { CustomTopBar(dataType) }
    ) {
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
        ) {
            LoadList(dataType, homeScreenViewModel, categoriesViewModel, navController)
        }
    }
}

@Composable
fun LoadList(
    dataType: String,
    homeScreenViewModel: HomeScreenViewModel,
    categoriesViewModel: CategoriesViewModel,
    navController: NavController
) {
    when (dataType) {
        "WishList" -> {
            val list = homeScreenViewModel.booksFromCart.value.data
            if (list.isNullOrEmpty())
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    ProgressBar()
                }
            else
                LazyColumn(
                    contentPadding = PaddingValues(10.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                ) {
                    items(list!!.size) {
                        SearchBookCard(
                            book = (list[it] as MBook).book!!,
                            fireStoreId = isBookInCart(
                                homeScreenViewModel,
                                (list[it] as MBook).book!!
                            ),
                            navController = navController
                        )
                    }
                }
        }

        else -> {
            LaunchedEffect(dataType) {
                categoriesViewModel.getCategoryBooks(dataType)
            }
            if (categoriesViewModel.loading || categoriesViewModel.data == null)
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    ProgressBar()
                }
            else if (categoriesViewModel.data!!.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("Something went wrong!!", fontSize = 15.sp, fontWeight = FontWeight.Bold)
                }
            }
            else
                LazyColumn(
                    contentPadding = PaddingValues(10.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    val list = categoriesViewModel.data
                    items(list!!.size) {
                        SearchBookCard(
                            book = list!![it],
                            fireStoreId = isBookInCart(
                                homeScreenViewModel,
                                list!![it]
                            ),
                            navController = navController
                        )
                    }
                }
        }
    }
}