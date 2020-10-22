package com.febryardiansyah.movieapp.api

import com.febryardiansyah.movieapp.models.GetMovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("movie/popular")
    fun getPopularMovies(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("page") page: Int
    ):Call<GetMovieResponse>

    @GET("movie/top_rated")
    fun getTopRatedMovies(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("page") page:Int
    ):Call<GetMovieResponse>

    @GET("movie/upcoming")
    fun getUpcomingMovies(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("page")page: Int
    ):Call<GetMovieResponse>
}

const val API_KEY = "9b63df8257f1c3cc6e88678c5c8525ed"