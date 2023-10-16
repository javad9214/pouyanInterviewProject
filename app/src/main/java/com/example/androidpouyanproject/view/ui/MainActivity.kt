package com.example.androidpouyanproject.view.ui

import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.core.view.WindowCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.androidpouyanproject.data.database.AppDatabase
import com.example.androidpouyanproject.data.entity.Post
import com.example.androidpouyanproject.data.repository.PostRepository
import com.example.androidpouyanproject.view.ui.fragments.FirstFragment
import com.example.androidpouyanproject.view.ui.fragments.SecondFragment
import com.example.androidpouyanproject.view.viewmodel.PostViewModel
import com.example.androidpouyanproject.view.viewmodel.PostViewModelFactory
import com.google.gson.Gson
import com.google.gson.JsonParser
import com.google.gson.reflect.TypeToken
import java.io.IOException

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)



        setContent {
           // InitDataBase()

            val navController = rememberNavController()

            NavHost(
                navController = navController,
                startDestination = "fragment1"
            ) {
                composable("fragment1") {
                    FirstFragment(navController)
                }
                composable("fragment2") {
                    SecondFragment(navController)
                }
            }
        }

    }

    @Composable
    fun InitDataBase(){
        val postDao = AppDatabase.getInstance(this).postDao()
        val postRepository = PostRepository(postDao)
        val postViewModel: PostViewModel = viewModel(factory = PostViewModelFactory(postRepository) )
        postViewModel.insertAllPosts(extractTheJsonToPost())
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

        Log.i("===>", "onCreate: App ,  posts : $posts")

        return posts
    }
}