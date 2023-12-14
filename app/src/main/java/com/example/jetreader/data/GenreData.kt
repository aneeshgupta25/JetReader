package com.example.jetreader.data

import com.example.jetreader.R
import com.example.jetreader.model.genre.Genre

object GenreData {
    fun getGenreData(): List<Genre> {
        return listOf(
            Genre("Thriller", R.drawable.thriller),
            Genre("Comics", R.drawable.comic),
            Genre("Inspiration", R.drawable.inspiration),
            Genre("Sci-Fi", R.drawable.scifi),
            Genre("Mystery", R.drawable.mystery),
            Genre("Action", R.drawable.action)
        )
    }
}