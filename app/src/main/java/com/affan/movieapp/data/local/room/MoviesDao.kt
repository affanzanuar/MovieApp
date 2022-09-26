package com.affan.movieapp.data.local.room


import androidx.room.*
import com.affan.movieapp.model.MoviesOrSeries

@Dao
interface MoviesDao {

    @Query("SELECT * FROM MoviesOrSeries")
    suspend fun getAllFavorit() : List<MoviesOrSeries>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(cinema : MoviesOrSeries)

    @Delete
    suspend fun deleteFavorite(cinema: MoviesOrSeries)

}