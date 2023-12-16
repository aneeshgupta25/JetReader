package com.example.jetreader.screens.onboard

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
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
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.PagerScope
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.jetreader.R
import com.example.jetreader.components.CustomButton
import com.example.jetreader.data.PagerData
import com.example.jetreader.navigation.ReaderScreens
import com.example.jetreader.utils.AppConstants
import kotlinx.coroutines.delay

@Preview
@Composable
fun OnboardScreen(
    navController: NavController = rememberNavController()
) {
    Column(
        modifier = Modifier
            .background(color = Color.White)
            .fillMaxSize()
    ) {
        OnboardPager()
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CustomButton(modifier = Modifier.padding(horizontal = 15.dp), backgroundColor = AppConstants.DarkYellow, textColor = Color.White, text = "Get Started", shadow = false) {
                navController.navigate(ReaderScreens.RegisterScreen.name)
            }
            Spacer(modifier = Modifier.height(20.dp))
            CustomButton(modifier = Modifier.padding(horizontal = 15.dp), backgroundColor = AppConstants.LightYellow, textColor = AppConstants.DarkYellow, text = "I already have an account", shadow = false) {
                navController.navigate(ReaderScreens.LoginScreen.name)
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardPager() {
    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f
    ) { PagerData.getPagerData().size }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.8f)
    ) {
        val list = PagerData.getPagerData()
        HorizontalPager(
            modifier = Modifier.fillMaxHeight(0.9f),
            state = pagerState,
            pageSpacing = 0.dp,
            userScrollEnabled = false,
            reverseLayout = false,
            contentPadding = PaddingValues(0.dp),
            beyondBoundsPageCount = 0,
            pageSize = PageSize.Fill,
            flingBehavior = PagerDefaults.flingBehavior(state = pagerState),
            key = null,
            pageNestedScrollConnection = PagerDefaults.pageNestedScrollConnection(
                Orientation.Horizontal
            ),
            pageContent = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(0.65f)
                    ) {
                        Image(
                            painter = painterResource(id = list[it].img),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight(),
                            contentScale = ContentScale.FillBounds
                        )
                    }
                    Text(
                        text = list[it].title,
                        modifier = Modifier.fillMaxWidth(),
                        fontWeight = FontWeight.Bold,
                        fontSize = 27.sp,
                        textAlign = TextAlign.Center,
                        lineHeight = 30.sp,
                    )
                    Text(
                        text = list[it].description,
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 20.dp),
                        textAlign = TextAlign.Center,
                        lineHeight = 25.sp,
                        fontSize = 17.sp
                    )
                }
            }
        )
        LaunchedEffect(true) {
            while(true) {
                delay(3000)
                pagerState.animateScrollToPage((pagerState.settledPage+1)%list.size)
            }
        }
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            repeat(list.size) {
                val color =
                    if (pagerState.currentPage == it) AppConstants.DarkYellow else Color.Gray
                Box(
                    modifier = Modifier
                        .padding(5.dp)
                        .width(15.dp)
                        .height(5.dp)
                        .clip(RoundedCornerShape(corner = CornerSize(10.dp)))
                        .background(color)
                )
            }
        }
    }
}