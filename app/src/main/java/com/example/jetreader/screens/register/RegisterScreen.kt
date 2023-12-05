package com.example.jetreader.screens.register

import android.annotation.SuppressLint
import android.graphics.Paint.Align
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetreader.R
import com.example.jetreader.components.CustomButton
import com.example.jetreader.utils.AppConstants
import kotlinx.coroutines.delay

@Preview
@Composable
fun RegisterScreen() {
    Column(
        modifier = Modifier
            .background(color = Color.White)
            .fillMaxSize()
    ) {
        RegisterPager()
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CustomButton(darkBackground = true, text = "Get Started", shadow = false)
            Spacer(modifier = Modifier.height(20.dp))
            CustomButton(darkBackground = false, text = "I already have an Account", shadow = false)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RegisterPager() {
    val pagerState = rememberPagerState(
        initialPage = 0
    )
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.8f)
    ) {
        HorizontalPager(
            state = pagerState,
            pageCount = 4,
            modifier = Modifier.fillMaxHeight(0.9f),
            userScrollEnabled = false
        ) { page ->
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
                        painter = painterResource(id = R.drawable.book1),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(),
                        contentScale = ContentScale.FillBounds
                    )
                }
                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                color = Color.Black,
                            )
                        ) {
                            append("Welcome to")
                        }
                        withStyle(
                            style = SpanStyle(
                                color = AppConstants.DarkYellow
                            )
                        ) {
                            append(" JetReader \uD83D\uDC4B")
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    fontWeight = FontWeight.Bold,
                    fontSize = 27.sp,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "Soar into a World of Words, " + "where each page carries you to new heights of imagination and " + "discovery.",
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 20.dp),
                    textAlign = TextAlign.Center,
                    lineHeight = 25.sp,
                    fontSize = 17.sp
                )
            }
        }
        LaunchedEffect(true) {
            while(true) {
                delay(3000)
                pagerState.animateScrollToPage((pagerState.settledPage+1)%4)
            }
        }
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            repeat(4) {
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