package com.example.androidpouyanproject.data.repository

import androidx.lifecycle.LiveData
import com.example.androidpouyanproject.data.dao.PostDao
import com.example.androidpouyanproject.data.entity.Post

class PostRepository(private val postDao: PostDao) {
    val allPosts: LiveData<List<Post>> = postDao.getAllPosts()

    suspend fun insertPost(posts: List<Post>){
        postDao.insertAll(posts)
    }
}