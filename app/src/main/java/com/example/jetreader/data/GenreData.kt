package com.example.jetreader.data

import com.example.jetreader.R
import com.example.jetreader.model.genre.Genre

object GenreData {
    fun getGenreData(): List<Genre> {
        return listOf(
            Genre("Thriller", R.drawable.thriller, "thriller"),
            Genre("Comics", R.drawable.comic, "comics"),
            Genre("Inspiration", R.drawable.inspiration, "inspiration"),
            Genre("Sci-Fi", R.drawable.scifi, "sci-fi"),
            Genre("Mystery", R.drawable.mystery, "mystery"),
            Genre("Action", R.drawable.action, "action")
        )
    }
}