package com.febryardiansyah.movieapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.febryardiansyah.movieapp.R
import com.febryardiansyah.movieapp.models.MovieModel

class MoviesAdapter(private var movies:MutableList<MovieModel>) : RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view:View = LayoutInflater.from(parent.context).inflate(R.layout.item_movie,parent,false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    fun append(movies:List<MovieModel>){
       this.movies.addAll(movies)
        notifyItemRangeInserted(
            this.movies.size,
            movies.size -1
        )
    }
    inner class MovieViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
        private val poster:ImageView = itemView.findViewById(R.id.img_popular_movie)

        fun bind(movie:MovieModel){
            Glide.with(itemView)
                .load("https://image.tmdb.org/t/p/w342${movie.posterPath}")
                .transform(CenterCrop())
                .into(poster)
        }
    }
}