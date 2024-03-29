package com.example.jetreader.screens.splash

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.jetreader.R
import com.example.jetreader.components.ProgressBar
import com.example.jetreader.navigation.ReaderScreens
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.delay


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SplashScreen(
    navController: NavController
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.surface)
    ) {
        val scale = remember {
            Animatable(0f)
        }
        LaunchedEffect(key1 = true) {
            delay(200)
            scale.animateTo(targetValue = 0.8f,
                animationSpec = tween(
                    durationMillis = 1000,
                    easing = {
                        OvershootInterpolator(8f).getInterpolation(it)
                    }
                )
            )
            delay(2000)
            if(FirebaseAuth.getInstance().currentUser?.email.isNullOrEmpty()) {
                navController.navigate(ReaderScreens.OnBoardScreen.name) {
                    popUpTo(ReaderScreens.SplashScreen.name) {
                        inclusive = true
                    }
                }
            } else {
                navController.navigate(ReaderScreens.HomeScreen.name) {
                    popUpTo(ReaderScreens.SplashScreen.name) {
                        inclusive = true
                    }
                }
            }
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier.fillMaxHeight(0.8f),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = if (isSystemInDarkTheme()) R.drawable.dlogo else R.drawable.llogo),
                    contentDescription = "Light Logo",
                    modifier = Modifier
                        .fillMaxHeight(0.5f)
                        .scale(scale.value),
                    contentScale = ContentScale.FillHeight
                )
            }
            Box(modifier = Modifier.fillMaxHeight(0.2f)) {
                ProgressBar(radius = 70f)
            }
        }
    }
}