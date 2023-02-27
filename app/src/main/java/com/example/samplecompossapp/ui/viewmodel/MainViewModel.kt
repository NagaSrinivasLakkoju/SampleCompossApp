package com.example.samplecompossapp.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.samplecompossapp.model.Movie
import com.example.samplecompossapp.network.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val apiService:ApiService):ViewModel(){
    var movieListResponse:List<Movie> by mutableStateOf(listOf())
    var errorMessage: String by mutableStateOf("")
    fun getMovieList(){
        viewModelScope.launch {
            try {
                val movieList=apiService.getMovies()
                movieListResponse=movieList
            } catch (e: Exception) {
                errorMessage=e.message.toString()
            }
        }
    }
}