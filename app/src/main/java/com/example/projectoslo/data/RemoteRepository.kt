package com.example.projectoslo.data

import com.example.projectoslo.models.NewsApiResponse
import com.example.projectoslo.util.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoteRepository @Inject constructor(
    private val api: PostService
) {
    fun getPosts(): Flow<NewsApiResponse> = flow {
        emit(api.getPosts(
            country = "us",
            category = "business",
            key = Constants.API_KEY
        ))
    }
}