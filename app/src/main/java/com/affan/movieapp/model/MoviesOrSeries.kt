package com.affan.movieapp.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

//@Entity
@Parcelize
@Entity
data class MoviesOrSeries(
    @PrimaryKey
    var id : Int = 0,
) : Parcelable
