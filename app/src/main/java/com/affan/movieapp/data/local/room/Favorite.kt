package com.affan.movieapp.data.local.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Favorite (
    @PrimaryKey (autoGenerate = false)
    val id : Int
        )