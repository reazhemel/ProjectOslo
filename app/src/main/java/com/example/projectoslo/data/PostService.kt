package com.example.projectoslo.data

import com.example.projectoslo.models.NewsApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PostService {

    @GET("top-headlines")
    suspend fun getPosts(
        @Query("country") country: String,
        @Query("category") category: String,
        @Query("apiKey") key: String
    ): NewsApiResponse
}