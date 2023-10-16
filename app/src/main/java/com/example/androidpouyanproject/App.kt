package com.example.androidpouyanproject

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.androidpouyanproject.data.database.AppDatabase
import com.example.androidpouyanproject.data.entity.Post
import com.example.androidpouyanproject.data.repository.PostRepository
import com.example.androidpouyanproject.view.viewmodel.PostViewModel
import com.example.androidpouyanproject.view.viewmodel.PostViewModelFactory
import com.google.gson.Gson
import com.google.gson.JsonParser
import com.google.gson.reflect.TypeToken
import java.io.IOException

class App : Application() {

    private val TAG = "===>"

    private lateinit var postDatabase: AppDatabase

    override fun onCreate() {
        super.onCreate()
        postDatabase = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "post-database"
        ).build()
        val postDao = db.postDao()

       extractTheJsonToPost()

        val postRepository = PostRepository(postDao)
        val postViewModel = ViewModelProvider(this, PostViewModelFactory(postRepository)).get(PostViewModel::class.java)

        postViewModel.insertAll(posts)


    }

    private fun readJsonFromAssets(context: Context, fileName: String): String? {
        return try {
            val inputStream = context.assets.open(fileName)
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            String(buffer, Charsets.UTF_8)
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }


    // Read and parse th JSON and map it into Post Model
    private fun extractTheJsonToPost() : List<Post>{
        // Read and parse the JSON file from the assets folder
        val jsonData = readJsonFromAssets(applicationContext, "init_data.json")

        val gson = Gson()
        val jsonObject = JsonParser.parseString(jsonData).asJsonObject
        val postsJsonArray = jsonObject.getAsJsonArray("posts")
        val listType = object : TypeToken<List<Post>>() {}.type
        val posts = gson.fromJson<List<Post>>(postsJsonArray, listType)

        Log.i(TAG, "onCreate: App ,  posts : $posts")

        return posts
    }
}