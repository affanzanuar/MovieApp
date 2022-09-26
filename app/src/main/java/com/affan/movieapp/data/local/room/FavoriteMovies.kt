package com.affan.movieapp.data.local.room

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


@Parcelize
@Entity
data class FavoriteMovies(
    @PrimaryKey (autoGenerate = false)
    var id : Int = 0,
    var name : String?,
    var title : String
) : Parcelable
