package com.febryardiansyah.movieapp.repositories

import com.febryardiansyah.movieapp.api.Api
import com.febryardiansyah.movieapp.models.GetMovieResponse
import com.febryardiansyah.movieapp.models.MovieModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MovieRepository {
    private val api: Api

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        api = retrofit.create(Api::class.java)
    }

    fun getPopularMovies(
        page: Int = 1, onSuccess: (movies: List<MovieModel>) -> Unit,
        onError: () -> Unit
    ) {
        api.getPopularMovies(page = page)
            .enqueue(object : Callback<GetMovieResponse> {
                override fun onResponse(
                    call: Call<GetMovieResponse>,
                    response: Response<GetMovieResponse>
                ) {
                    if (response.isSuccessful) {
                        val body = response.body()

                        if (body != null) {
                            onSuccess.invoke(body.movies)
                        } else {
                            onError.invoke()
                        }
                    }
                }

                override fun onFailure(call: Call<GetMovieResponse>, t: Throwable) {
                    onError.invoke()
                }
            })
    }

    fun getTopRatedMovies(
        page: Int = 1, onSuccess: (movies: List<MovieModel>) -> Unit,
        onError: () -> Unit
    ) {
        api.getTopRatedMovies(page = page).enqueue(object : Callback<GetMovieResponse> {
            override fun onResponse(
                call: Call<GetMovieResponse>,
                response: Response<GetMovieResponse>
            ) {
                val body = response.body()
                if (response.isSuccessful) {
                    if (body != null) {
                        onSuccess.invoke(body.movies)
                    } else {
                        onError.invoke()
                    }
                } else {
                    onError.invoke()
                }
            }

            override fun onFailure(call: Call<GetMovieResponse>, t: Throwable) {
                onError.invoke()
            }
        })
    }

    fun getUpcomingMovies(
        page: Int = 1,
        onSuccess: (movies: List<MovieModel>) -> Unit,
        onError: () -> Unit
    ) {
        api.getUpcomingMovies(page = page).enqueue(object :Callback<GetMovieResponse>{
            override fun onResponse(
                call: Call<GetMovieResponse>,
                response: Response<GetMovieResponse>
            ) {
                if (response.isSuccessful){
                    val body = response.body()
                    if(body !=null){
                        onSuccess.invoke(body.movies)
                    }else{
                        onError.invoke()
                    }
                }else{
                    onError.invoke()
                }
            }

            override fun onFailure(call: Call<GetMovieResponse>, t: Throwable) {
                onError.invoke()
            }
        })
    }
}