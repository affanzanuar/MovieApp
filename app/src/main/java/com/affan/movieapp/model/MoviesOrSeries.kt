package com.affan.movieapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

//@Entity
@Parcelize
data class MoviesOrSeries(
    var id : Int = 0,
) : Parcelable
