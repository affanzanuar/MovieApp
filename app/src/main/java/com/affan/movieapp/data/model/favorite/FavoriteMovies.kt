package com.affan.movieapp.data.model.favorite

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


@Parcelize
@Entity
data class FavoriteMovies(
    @PrimaryKey (autoGenerate = false)
    var id : Int?,
    var name : String?,
    var title : String?,
    var poster : String?
) : Parcelable
