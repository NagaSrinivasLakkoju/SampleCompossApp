package com.example.samplecompossapp.network

import com.example.samplecompossapp.model.Movie
import com.example.samplecompossapp.utils.AppConstants.Companion.MOVIE_ENDPOINT
import retrofit2.http.GET

interface ApiService {
    @GET(MOVIE_ENDPOINT)
    suspend fun getMovies() : List<Movie>
}