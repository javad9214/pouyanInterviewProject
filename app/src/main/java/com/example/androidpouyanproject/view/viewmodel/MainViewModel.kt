package com.example.androidpouyanproject.view.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {
    private val _currentFragment = mutableStateOf("fragment1")

    val currentFragment: State<String> = _currentFragment

    fun navigateToFragment2() {
        _currentFragment.value = "fragment2"
    }
}