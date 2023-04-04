package com.example.samplecompossapp.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.samplecompossapp.model.Movie
import com.example.samplecompossapp.network.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val apiService:ApiService):ViewModel(){
    //private var movieListResponse:List<Movie> by mutableStateOf(listOf())
    var errorMessage: String by mutableStateOf("")
    private val _uiState = MutableStateFlow<MovieUiState>(MovieUiState.Empty)
    val uiState: StateFlow<MovieUiState> = _uiState


    fun getMovieList(){
        _uiState.value = MovieUiState.Loading
        viewModelScope.launch {
            try {
                val movieList=apiService.getMovies()
                _uiState.value=MovieUiState.Loaded(movieList)
            } catch (e: Exception) {
                errorMessage=e.message.toString()
                _uiState.value = MovieUiState.Error(errorMessage)
            }
        }
    }


    sealed class MovieUiState {
        object Empty : MovieUiState()
        object Loading : MovieUiState()
        class Loaded(val data: List<Movie>) : MovieUiState()
        class Error(val message: String) : MovieUiState()
    }
}