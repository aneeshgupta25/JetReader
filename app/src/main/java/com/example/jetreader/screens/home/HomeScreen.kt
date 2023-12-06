package com.example.jetreader.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Card
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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetreader.R
import com.example.jetreader.components.BookCard
import com.example.jetreader.utils.AppConstants

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun HomeScreen() {
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    Scaffold(topBar = {
        HomeTopBar()
    }) {
        Box(modifier = Modifier.padding(it)) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 10.dp)
                    .scrollable(rememberScrollState(), orientation = Orientation.Vertical)
            ) {
                Categories(isCategory = false) {
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        items(10) {
                            BookCard(
                                modifier = Modifier
                                    .height(screenHeight / 4)
                                    .width(screenWidth / 3),
                                textSize = 12.sp,
                                ratingSize = 10.sp
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
                        items(count = 10) {
                            Box(
                                modifier = Modifier
                                    .width(screenWidth / 2)
                                    .height(screenHeight / 8)
                                    .clip(RoundedCornerShape(corner = CornerSize(10.dp)))
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.book1),
                                    contentDescription = "genre",
                                    contentScale = ContentScale.FillBounds
                                )
                                Text(
                                    text = "Hello",
                                    color = Color.Black,
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
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        items(10) {
                            BookCard(
                                modifier = Modifier
                                    .height(screenHeight / 4)
                                    .width(screenWidth / 3),
                                textSize = 12.sp,
                                ratingSize = 10.sp
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun HomeTopBar() {
    Row(
        modifier = Modifier
            .padding(vertical = 10.dp)
            .fillMaxHeight(0.1f)
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
            onClick = { /*TODO*/ },
        ) {
            Icon(
                modifier = Modifier.fillMaxSize(0.7f),
                painter = painterResource(id = R.drawable.search),
                contentDescription = "Search",
            )
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
        if(isCategory) Row(
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