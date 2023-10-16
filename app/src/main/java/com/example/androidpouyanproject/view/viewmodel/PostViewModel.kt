package com.example.androidpouyanproject.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidpouyanproject.data.entity.Post
import com.example.androidpouyanproject.data.repository.PostRepository
import kotlinx.coroutines.launch

class PostViewModel(private val repository: PostRepository): ViewModel() {
    val allPosts: LiveData<List<Post>> = repository.allPosts


    fun insertPost(posts: List<Post>) {
        viewModelScope.launch {
            repository.insertPost(posts)
        }
    }
}