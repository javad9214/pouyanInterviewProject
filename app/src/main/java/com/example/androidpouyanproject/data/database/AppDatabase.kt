package com.example.androidpouyanproject.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.androidpouyanproject.data.dao.PostDao
import com.example.androidpouyanproject.data.entity.Post

@Database(entities = [Post::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun postDao():PostDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        // Create a custom getInstance method
        fun getInstance(context: Context): AppDatabase {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "post-database"
                    ).build()
                }
            }
            return INSTANCE!!
        }
    }
}