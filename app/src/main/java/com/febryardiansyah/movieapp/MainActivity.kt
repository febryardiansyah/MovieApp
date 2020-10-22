package com.febryardiansyah.movieapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.febryardiansyah.movieapp.adapters.MoviesAdapter
import com.febryardiansyah.movieapp.models.MovieModel
import com.febryardiansyah.movieapp.repositories.MovieRepository

class MainActivity : AppCompatActivity() {
    private lateinit var rvPopularMovies:RecyclerView
    private lateinit var popularMoviesAdapter: MoviesAdapter
    private lateinit var lmPopularMovies: LinearLayoutManager
    private var popularMoviePage:Int = 1

    private lateinit var rvTopRatedMovies:RecyclerView
    private lateinit var topRatedAdapter: MoviesAdapter
    private lateinit var lmTopRated:LinearLayoutManager
    private var topRatedPage:Int = 1

    private lateinit var rvUpcomingMovies:RecyclerView
    private lateinit var upcomingMoviesAdapter: MoviesAdapter
    private lateinit var lmUpcomingMovies:LinearLayoutManager
    private var upcomingPage:Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //popular movies
        rvPopularMovies = findViewById(R.id.rv_popular_movies)
        lmPopularMovies = LinearLayoutManager(
            this,LinearLayoutManager.HORIZONTAL,false
        )
        rvPopularMovies.layoutManager = lmPopularMovies
        popularMoviesAdapter = MoviesAdapter(mutableListOf())
        rvPopularMovies.adapter = popularMoviesAdapter

        //top rated movies
        rvTopRatedMovies = findViewById(R.id.rv_top_rated)
        lmTopRated = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        rvTopRatedMovies.layoutManager = lmTopRated
        topRatedAdapter = MoviesAdapter(mutableListOf())
        rvTopRatedMovies.adapter = topRatedAdapter

        //upcoming movies
        rvUpcomingMovies = findViewById(R.id.rv_upcoming_movies)
        lmUpcomingMovies = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        rvUpcomingMovies.layoutManager = lmUpcomingMovies
        upcomingMoviesAdapter = MoviesAdapter(mutableListOf())
        rvUpcomingMovies.adapter = upcomingMoviesAdapter

        getPopularMovies()
        getTopRatedMovies()
        getUpcomingMovies()
    }

    private fun getPopularMovies(){
        MovieRepository.getPopularMovies(
            popularMoviePage,
            onSuccess = ::onPopularMoviesFetched,
            onError = ::onError
        )
    }

    private fun onPopularMoviesFetched(movies:List<MovieModel>){
        popularMoviesAdapter.append(movies)
        attachPopularMoviesOnScrollListener()

    }
    private fun onError(){
        Toast.makeText(this,getString(R.string.error_fetch_movies),Toast.LENGTH_SHORT).show()
    }


    private fun attachPopularMoviesOnScrollListener(){
        rvPopularMovies.addOnScrollListener(object :RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                //total number of movies inside popularMoviesAdapter
                val totalItems = lmPopularMovies.itemCount
                //- the current number of child views attached to the RecyclerView that are currently being recycled over
                //and over again
                val visibleItemCount = lmPopularMovies.childCount
                //the position of the leftmost visible item in our list.
                val firstVisibleItem = lmPopularMovies.findFirstCompletelyVisibleItemPosition()

                if(firstVisibleItem + visibleItemCount >= totalItems/2){
                    rvPopularMovies.removeOnScrollListener(this)
//                    Log.d("Main Activity","Fetching Movies")
                    popularMoviePage ++
                    getPopularMovies()
                }
            }
        })
    }

    private fun getTopRatedMovies(){
        MovieRepository.getTopRatedMovies(
            topRatedPage,
            onSuccess = ::onTopRatedFetched,
            onError = ::onError
        )
    }

    private fun onTopRatedFetched(movies: List<MovieModel>){
        topRatedAdapter.append(movies)
        attachTopRatedMoviesScrollListener()
    }

    private fun attachTopRatedMoviesScrollListener(){
        rvTopRatedMovies.addOnScrollListener(object :RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                //total number of movies inside popularMoviesAdapter
                val totalItems = lmTopRated.itemCount
                //- the current number of child views attached to the RecyclerView that are currently being recycled over
                //and over again
                val visibleItemCount = lmTopRated.childCount
                //the position of the leftmost visible item in our list.
                val firstVisibleItem = lmTopRated.findFirstCompletelyVisibleItemPosition()

                if(firstVisibleItem + visibleItemCount >= totalItems/2){
                    rvTopRatedMovies.removeOnScrollListener(this)
                    topRatedPage ++
                    getTopRatedMovies()
                }
            }
        })
    }

    private fun getUpcomingMovies(){
        MovieRepository.getUpcomingMovies(
            page = upcomingPage,
            onSuccess = ::onUpcomingFetched,
            onError = ::onError
        )
    }

    private fun onUpcomingFetched(movies:List<MovieModel>){
        upcomingMoviesAdapter.append(movies)
        attachUpcomingMoviesScrollListener()
    }

    private fun attachUpcomingMoviesScrollListener(){
        rvUpcomingMovies.addOnScrollListener(object :RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                //total number of movies inside popularMoviesAdapter
                val totalItems = lmUpcomingMovies.itemCount
                //- the current number of child views attached to the RecyclerView that are currently being recycled over
                //and over again
                val visibleItemCount = lmUpcomingMovies.childCount
                //the position of the leftmost visible item in our list.
                val firstVisibleItem = lmUpcomingMovies.findFirstCompletelyVisibleItemPosition()

                if(firstVisibleItem + visibleItemCount >= totalItems/2){
                    rvUpcomingMovies.removeOnScrollListener(this)
                    upcomingPage ++
                    getTopRatedMovies()
                }
            }
        })
    }
}