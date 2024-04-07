package com.example.testkoinretrofit.network

import com.example.testkoinretrofit.data.model.Post
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("posts/1")
    suspend fun getPost(): Response<Post>
}