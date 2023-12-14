package com.example.jetreader.screens.book

import android.util.Log
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.HtmlCompat
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.jetreader.R
import com.example.jetreader.components.CustomButton
import com.example.jetreader.components.CustomTopBar
import com.example.jetreader.components.ProgressBar
import com.example.jetreader.model.volume.Book
import com.example.jetreader.utils.AppConstants

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookDetailScreen(
    bookId: String,
    bookDetailsViewModel: BookDetailsViewModel,
    addedToCart: Boolean = false
) {
    Scaffold(
        topBar = {
            CustomTopBar()
        }
    ) {

        LaunchedEffect(true) {
            bookDetailsViewModel.getBookDetails(bookId)
        }

        var bookData = bookDetailsViewModel.bookDetails
        Box(modifier = Modifier.padding(it)) {
            if (bookDetailsViewModel.loading || bookData == null)
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    ProgressBar()
                }
            else
                BookDetails(bookData = bookData, addedToCart = addedToCart)
        }
    }
}

@Composable
fun BookDetails(bookData: Book, addedToCart: Boolean) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 15.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        BookHeader(bookData = bookData)
        BookAbout(bookData = bookData)
        CustomButton(
            backgroundColor = if (addedToCart) Color.Red else AppConstants.DarkYellow,
            textColor = Color.White,
            text = if (addedToCart) "Remove from Cart" else "Add to Cart"
        )
    }
}

@Composable
fun BookAbout(bookData: Book) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(
                AppConstants
                    .getScreenHeightInDp()
                    .times(0.55f)
            )
            .padding(vertical = 20.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(
            text = "About this Ebook", fontWeight = FontWeight.Bold,
            fontSize = 25.sp
        )
        Column(
            modifier = Modifier.verticalScroll(state = rememberScrollState())
        ) {
            val cleanDescription = bookData?.volumeInfo?.description?.let { it1 ->
                HtmlCompat.fromHtml(
                    it1,
                    HtmlCompat.FROM_HTML_MODE_LEGACY
                ).toString()
            }
            Text(
                text = cleanDescription ?: "Unable to fetch Data!",
                fontSize = 18.sp
            )
        }
    }
}

@Composable
fun BookHeader(bookData: Book) {
    Row(
        modifier = Modifier
            .height(
                AppConstants
                    .getScreenHeightInDp()
                    .times(0.30f)
            )
            .fillMaxWidth()
    ) {
        var imageUrl = bookData?.volumeInfo?.imageLinks?.thumbnail?.replace(
            "http://",
            "https://"
        )
            ?: "https://images.theconversation.com/files/45159/original/rptgtpxd-1396254731.jpg?ixlib=rb-1.1.0&q=45&auto=format&w=754&fit=clip"
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
                .crossfade(true)
                .build(),
            modifier = Modifier
                .clip(RoundedCornerShape(corner = CornerSize(10.dp)))
                .fillMaxWidth(0.4f)
                .fillMaxHeight(),
            onError = {
                Log.d("Erorr", "BookCard: Error ${bookData.id}")
            },
            contentDescription = "Image of Book",
            contentScale = ContentScale.FillBounds
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 10.dp)
                .verticalScroll(state = rememberScrollState(), enabled = true),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = bookData?.volumeInfo?.title ?: "",
                    fontSize = 21.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 30.sp
                )
                if (bookData?.volumeInfo?.subtitle != null) {
                    Text(
                        text = "- ${bookData.volumeInfo.subtitle}",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        lineHeight = 25.sp
                    )
                }
                if (bookData?.volumeInfo?.categories != null) {
                    Text(
                        text = "Categories - ${
                            bookData.volumeInfo.categories.toString().replace("[", "").replace("]", "")
                        }",
                        fontSize = 16.sp,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
            }
            Column(
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = bookData?.volumeInfo?.authors.toString()
                        .replace("[", "").replace("]", "") ?: "",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = AppConstants.DarkYellow
                )
                Text(
                    text = bookData?.volumeInfo?.publishedDate ?: "",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}
