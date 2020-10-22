package com.febryardiansyah.movieapp.models

import com.google.gson.annotations.SerializedName

data class GetMovieResponse(
    @SerializedName("page") val page:Int,
    @SerializedName("results") val movies:List<MovieModel>,
    @SerializedName("total_pages")val pages:Int
)