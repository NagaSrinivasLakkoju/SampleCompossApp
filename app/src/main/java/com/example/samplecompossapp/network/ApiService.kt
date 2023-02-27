package com.example.samplecompossapp.network

import com.example.samplecompossapp.model.Movie
import com.example.samplecompossapp.utils.AppConstants.Companion.MOVIE_ENDPOINT
import retrofit2.http.GET

interface ApiService {
    @GET(MOVIE_ENDPOINT)
    suspend fun getMovies() : List<Movie>

 /*   companion object {
        var apiService: ApiService? = null
        fun getInstance() : ApiService {
            if (apiService == null) {
                apiService = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(ApiService::class.java)
            }
            return apiService!!
        }
    }*/
}