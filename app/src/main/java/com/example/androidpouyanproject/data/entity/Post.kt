package com.example.androidpouyanproject.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Post (
    @PrimaryKey(autoGenerate = true) val id: Int = 0 ,
    val full_thumbnail_url: String,
    val caption: String,
    val likesCount: Int,
    val commentsCount: Int
)