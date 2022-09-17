package com.affan.movieapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MoviesOrSeries(
    var id : Int = 0,
    var moviesOrSeriesTitle : String = "",
    var moviesOrSeriesPoster : Int = 0,
    var moviesOrSeriesBackDrop : Int = 0,
    var moviesOrSeriesGenre : String = "",
    var moviesOrSeriesRating : String = "",
    var moviesOrSeriesIsAdult : Boolean = false,
    var moviesOrSeriesDescription: String = "",
    var releaseDate : String = "",
    var originalLanguage : String = "",
    var voteCount : Int = 0,
) : Parcelable
