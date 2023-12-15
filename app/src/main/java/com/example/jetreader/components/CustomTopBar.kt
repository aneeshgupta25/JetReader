package com.example.jetreader.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetreader.utils.AppConstants

@Preview
@Composable
fun CustomTopBar(
    title: String = "Hello"
) {
    Row(
        modifier = Modifier
            .padding(vertical = 5.dp, horizontal = 10.dp)
            .height(
                AppConstants
                    .getScreenHeightInDp()
                    .times(0.075f)
            )
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "Back"
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(text = title,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,

        )
    }
}