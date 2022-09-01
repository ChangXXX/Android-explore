package com.study.moviesearch.data.movie.data

import com.google.gson.annotations.SerializedName

data class SearchResult(
    val lastBuildDate: String,
    val total: Int,
    val start: Int,
    val display: Int,
    @SerializedName("items") val items: Array<Movie>
)
