package com.example.testkoinretrofit.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testkoinretrofit.data.repository.PostRepository
import com.example.testkoinretrofit.data.model.Post
import kotlinx.coroutines.launch

class MyViewModel(private val repository: PostRepository) : ViewModel() {
    private val _post = MutableLiveData<Post>()
    val post: LiveData<Post> = _post

    fun fetchPost() {
        viewModelScope.launch {
            try {
                val response = repository.getPost()
                if (response.isSuccessful) {
                    _post.value = response.body()
                } else {
                    // Handle error
                    Log.e("MyViewModel", "Failed to fetch post: ${response.code()}")
                }
            } catch (e: Exception) {
                // Handle error
                Log.e("MyViewModel", "Error fetching post: ${e.message}")
            }
        }
    }
}