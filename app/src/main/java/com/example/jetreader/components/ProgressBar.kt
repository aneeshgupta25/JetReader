package com.example.jetreader.components

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetreader.utils.AppConstants
import kotlinx.coroutines.delay
import kotlin.math.cos
import kotlin.math.sin

@Preview
@Composable
fun ProgressBar(radius: Float = 60f) {
    var progressState = remember {
        mutableStateOf(0f)
    }
    LaunchedEffect(Unit) {
        while (true) {
            delay(40)
            progressState.value = (progressState.value + 30) % 360f
        }
    }
    Canvas(modifier = Modifier.size(150.dp)) {
        val centerX = size.width/2
        val centerY = size.height/2

        for(i in 0..12) {
            val r = radius/5 - (radius/80)*i
            val angle = Math.toRadians((30*i - progressState.value).toDouble())
            val x = centerX + (radius * sin(angle).toFloat())
            val y = centerY + (radius * cos(angle).toFloat())

            drawCircle(AppConstants.DarkYellow, r, Offset(x,y))
        }
    }
}