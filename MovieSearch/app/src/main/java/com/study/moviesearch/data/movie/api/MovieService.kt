package com.study.moviesearch.data.movie.api

import com.study.moviesearch.data.movie.data.SearchResult
import com.study.moviesearch.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {

    @GET("search/{type}")
    suspend fun getMovies(
        @Header("X-Naver-Client-Id") id: String,
        @Header("X-Naver-Client-Secret") secret: String,
        @Path("type") type: String,
        @Query("query") query: String
    ): Response<SearchResult>
}
