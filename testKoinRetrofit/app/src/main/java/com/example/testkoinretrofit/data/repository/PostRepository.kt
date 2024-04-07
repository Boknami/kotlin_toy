package com.example.testkoinretrofit.data.repository

import com.example.testkoinretrofit.data.model.Post
import retrofit2.Response

interface PostRepository {
    suspend fun getPost(): Response<Post>
}
