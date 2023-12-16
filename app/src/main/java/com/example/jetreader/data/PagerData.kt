package com.example.jetreader.data

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import com.example.jetreader.R
import com.example.jetreader.model.pager.MyPager
import com.example.jetreader.utils.AppConstants

object PagerData {
    fun getPagerData(): List<MyPager> {
        return listOf(
            MyPager(
                R.drawable.book1,
                "Soar into a World of Words, " + "where each page carries you to new heights of imagination and " + "discovery.",
                buildAnnotatedString {
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
                }
            ),
            MyPager(
                R.drawable.mbook2,
                "Access Your Favorite Books Anytime, Anywhere – JetReader Makes It Possible",
                buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = Color.Black,
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
                R.drawable.mbook3,
                "Browse Our Extensive Library and Add Books to Your Cart with Ease",
                buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = Color.Black,
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
                R.drawable.mbook4,
                "Immersive, Intuitive, and Personalized – JetReader Redefines Your Reading Experience",
                buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = Color.Black,
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

