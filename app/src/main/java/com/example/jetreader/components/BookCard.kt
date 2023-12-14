package com.example.jetreader.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import com.example.jetreader.R
import com.example.jetreader.model.volume.Book
import com.example.jetreader.navigation.ReaderScreens

@Composable
fun BookCard(
    modifier: Modifier = Modifier,
    rating: String = "4.3",
    textSize: TextUnit = 15.sp,
    authorSize: TextUnit = 12.sp,
    ratingSize: TextUnit = 10.sp,
    book: Book?,
    navController: NavController
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.clickable {
            navController.navigate(ReaderScreens.BookDetailsScreen.name+"/${book!!.id}")
        }
    ) {
        Card(
            modifier = Modifier
                .fillMaxHeight(0.7f)
                .fillMaxWidth(),
            shape = RoundedCornerShape(corner = CornerSize(10.dp)),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            val imageUrl =
                if (book!!.volumeInfo.imageLinks == null || book.volumeInfo.imageLinks!!.thumbnail.isNullOrEmpty())
                    "https://images.theconversation.com/files/45159/original/rptgtpxd-1396254731.jpg?ixlib=rb-1.1.0&q=45&auto=format&w=754&fit=clip"
                else
                    book.volumeInfo.imageLinks!!.thumbnail.replace("http://", "https://")
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(imageUrl)
                    .crossfade(true)
                    .build(),
                modifier = Modifier.fillMaxSize(),
                onError = {
                    Log.d("Error", "BookCard: Error ${book.id}")
                },
                contentDescription = "Image of Book",
                contentScale = ContentScale.FillBounds
            )
        }
        Spacer(modifier = Modifier.height(5.dp))
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .background(Color.White),
        ) {
            Column(
                modifier = Modifier.fillMaxHeight(0.6f).fillMaxWidth()
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = book!!.volumeInfo.title,
                    fontSize = textSize,
                    fontWeight = FontWeight.Bold,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(5.dp))
                var authors: String = ""
                if(book.volumeInfo.authors != null) {
                    for (i in 0 until book.volumeInfo.authors.size) {
                        authors += book.volumeInfo.authors[i]
                        if (i != book.volumeInfo.authors.size - 1) authors += ", "
                    }
                }
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = authors,
                    fontSize = authorSize,
                    fontWeight = FontWeight.Medium,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxSize()
            ) {
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = "Rating",
                    modifier = Modifier.size(20.dp),
                    tint = Color.Gray
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text = if (book!!.volumeInfo.averageRating == null) rating else book!!.volumeInfo.averageRating.toString(),
                    fontSize = ratingSize,
                    fontWeight = FontWeight.Medium,
                    color = Color.Gray
                )
            }
        }
    }
}