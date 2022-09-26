package com.affan.movieapp.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

//@Entity
@Parcelize
@Entity
data class MoviesOrSeries(
    @PrimaryKey (autoGenerate = false)
    var id : Int = 0,
) : Parcelable
