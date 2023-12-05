package com.example.jetreader.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetreader.utils.AppConstants

@Preview
@Composable
fun CustomButton(
    text: String = "Get Started",
    darkBackground: Boolean = false,
    shadow: Boolean = false,
    onClick: () -> Unit = {}
) {
    Button(onClick = onClick,
        modifier = Modifier.fillMaxWidth()
            .height(50.dp)
            .padding(horizontal = 15.dp),
        contentPadding = PaddingValues(0.dp)
    ) {
        Card(
            modifier = Modifier
                .fillMaxSize(),
            shape = RoundedCornerShape(corner = CornerSize(20.dp)),
            elevation = CardDefaults.cardElevation(defaultElevation = if (shadow) 10.dp else 0.dp),
            colors = CardDefaults.cardColors(
                if (darkBackground) AppConstants.DarkYellow
                else AppConstants.LightYellow
            )
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = text,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontSize = 15.sp,
                    color = if (darkBackground) Color.White else AppConstants.DarkYellow,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}