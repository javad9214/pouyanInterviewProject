package com.example.androidpouyanproject.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.androidpouyanproject.data.entity.Post

@Dao
interface PostDao {

    @Insert
    suspend fun insertAll(posts: List<Post>)

    @Query("SELECT * FROM Post")
    fun getAllPosts(): LiveData<List<Post>>

}