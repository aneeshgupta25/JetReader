package com.example.jetreader.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.jetreader.model.volume.Book
import com.example.jetreader.navigation.ReaderScreens
import com.example.jetreader.utils.AppConstants

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBookCard(
    book: Book,
    fireStoreId: String?,
    navController: NavController,
) {
    Card(
        modifier = Modifier
            .height(
                AppConstants
                    .getScreenHeightInDp()
                    .times(0.20f)
            )
            .fillMaxWidth()
            .background(color = Color.Transparent),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
        onClick =
        { navController.navigate(ReaderScreens.BookDetailsScreen.name + "/${book!!.id}/${fireStoreId}") }
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(0.3f),
                shape = RoundedCornerShape(corner = CornerSize(10.dp)),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                var imageUrl =
                    book?.volumeInfo?.imageLinks?.thumbnail?.replace("http://", "https://")
                        ?: "https://images.theconversation.com/files/45159/original/rptgtpxd-1396254731.jpg?ixlib=rb-1.1.0&q=45&auto=format&w=754&fit=clip"
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(imageUrl)
                        .crossfade(true)
                        .build(),
                    modifier = Modifier.fillMaxSize(),
                    onError = {
                        Log.d("Error", "BookCard: Error $it")
                    },
                    contentDescription = "Image of Book",
                    contentScale = ContentScale.FillBounds
                )
            }
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Text(
                    text = book!!.volumeInfo!!.title!!,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )
                if (!book.volumeInfo!!.subtitle.isNullOrEmpty())
                    Text(
                        text = book.volumeInfo!!.subtitle!!,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(5.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Star, contentDescription = "Rating",
                        modifier = Modifier.size(15.dp),
                        tint = Color.Gray
                    )
                    Text(
                        text = if (book?.volumeInfo?.averageRating != null) book.volumeInfo!!.averageRating.toString() else "4.2",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Gray
                    )
                }
                if (book?.volumeInfo?.pageCount != null)
                    Text(
                        text = if (book?.volumeInfo?.pageCount != null) "${book.volumeInfo?.pageCount} pages" else "",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium
                    )
            }
        }
    }
}