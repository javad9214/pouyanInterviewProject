package com.example.androidpouyanproject.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.androidpouyanproject.data.dao.PostDao
import com.example.androidpouyanproject.data.entity.Post

@Database(entities = [Post::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun postDao():PostDao
}