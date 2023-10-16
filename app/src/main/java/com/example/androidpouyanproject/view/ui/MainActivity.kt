package com.example.androidpouyanproject.view.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.androidpouyanproject.view.ui.fragments.FirstFragment
import com.example.androidpouyanproject.view.ui.fragments.SecondFragment

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
          //  val viewModel = viewModel<MainViewModel>()

            NavHost(
                navController = navController,
                startDestination = "fragment1"
            ) {
                composable("fragment1") {
                    FirstFragment(navController)
                }
                composable("fragment2") {
                    SecondFragment()
                }
            }
        }
    }
}