package com.example.jetreader.data

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import com.example.jetreader.R
import com.example.jetreader.model.pager.MyPager
import com.example.jetreader.utils.AppConstants

object PagerData {
    @Composable
    fun getPagerData(): List<MyPager> {
        return listOf(
            MyPager(
                if(isSystemInDarkTheme()) R.drawable.book1 else R.drawable.mbook1,
                "Soar into a World of Words, " + "where each page carries you to new heights of imagination and " + "discovery.",
                buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = MaterialTheme.colorScheme.onPrimary
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
                }
            ),
            MyPager(
                if(isSystemInDarkTheme()) R.drawable.book2 else R.drawable.mbook2,
                "Access Your Favorite Books Anytime, Anywhere – JetReader Makes It Possible",
                buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = MaterialTheme.colorScheme.onPrimary,
                        )
                    ) {
                        append("Carry Your Library in Your")
                    }
                    withStyle(
                        style = SpanStyle(
                            color = AppConstants.DarkYellow
                        )
                    ) {
                        append(" Pocket \uD83D\uDC5D")
                    }
                }
            ),
            MyPager(
                if(isSystemInDarkTheme()) R.drawable.book3 else R.drawable.mbook3,
                "Browse Our Extensive Library and Add Books to Your Cart with Ease",
                buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = MaterialTheme.colorScheme.onPrimary,
                        )
                    ) {
                        append("Explore & Collect")
                    }
                    withStyle(
                        style = SpanStyle(
                            color = AppConstants.DarkYellow
                        )
                    ) {
                        append(" Literary Gems \uD83D\uDC8E")
                    }
                }
            ),
            MyPager(
                if(isSystemInDarkTheme()) R.drawable.book4 else R.drawable.mbook4,
                "Immersive, Intuitive, and Personalized – JetReader Redefines Your Reading Experience",
                buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = MaterialTheme.colorScheme.onPrimary,
                        )
                    ) {
                        append("Enjoy Seamless")
                    }
                    withStyle(
                        style = SpanStyle(
                            color = AppConstants.DarkYellow
                        )
                    ) {
                        append(" Reading \uD83D\uDCD6")
                    }
                }
            )
        )
    }
}

