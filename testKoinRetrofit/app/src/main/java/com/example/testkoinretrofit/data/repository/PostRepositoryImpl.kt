package com.example.testkoinretrofit.data.repository

import com.example.testkoinretrofit.network.ApiService
import com.example.testkoinretrofit.data.model.Post
import retrofit2.Response


class PostRepositoryImpl(private val apiService: ApiService) : PostRepository {
    override suspend fun getPost(): Response<Post> {
        return apiService.getPost()
    }
}