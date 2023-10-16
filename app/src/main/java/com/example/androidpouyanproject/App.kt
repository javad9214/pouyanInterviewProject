package com.example.androidpouyanproject

import android.app.Application
import androidx.room.Room
import com.example.androidpouyanproject.data.database.AppDatabase

class App : Application() {

    private lateinit var postDatabase: AppDatabase

    override fun onCreate() {
        super.onCreate()
        postDatabase = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "post-database"
        ).build()

    }

}