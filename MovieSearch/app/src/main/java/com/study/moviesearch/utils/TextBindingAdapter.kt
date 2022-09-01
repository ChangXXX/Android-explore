package com.study.moviesearch.utils

import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("movieTitle")
fun movieTitle(view: TextView, title: String) {
    val reg = title.replace("</b>".toRegex(), "")
    val text = "제목 : ${reg.replace("<b>".toRegex(), "")}"
    view.text = text
}

@BindingAdapter("moviePubDate")
fun moviePubDate(view: TextView, pubDate: String) {
    val text = "출시 : $pubDate"
    view.text = text
}

@BindingAdapter("movieRating")
fun movieRating(view: TextView, rating: String) {
    val text = "평점 : $rating"
    view.text = text
}
