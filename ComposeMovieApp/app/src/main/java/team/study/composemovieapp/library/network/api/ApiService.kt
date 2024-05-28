package team.study.composemovieapp.library.network.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Url

interface ApiService {

    @GET
    suspend fun get(
        @Url url: String,
        @HeaderMap headerMap: Map<String, String>,
    ): Response<String>

    @POST
    suspend fun post(
        @Url url: String,
        @HeaderMap headerMap: Map<String, String>,
    ): Response<String>

    @POST
    suspend fun post(
        @Url url: String,
        @HeaderMap headerMap: Map<String, String>,
        @Body body: Any,
    ): Response<String>

    @PUT
    suspend fun put(
        @Url url: String,
        @HeaderMap headerMap: Map<String, String>,
    ): Response<String>

    @PUT
    suspend fun put(
        @Url url: String,
        @HeaderMap headerMap: Map<String, String>,
        @Body body: Any,
    ): Response<String>

    @DELETE
    suspend fun delete(
        @Url url: String,
        @HeaderMap headerMap: Map<String, String>,
    ): Response<String>

    @DELETE
    suspend fun delete(
        @Url url: String,
        @HeaderMap headerMap: Map<String, String>,
        @Body body: Any,
    ): Response<String>
}