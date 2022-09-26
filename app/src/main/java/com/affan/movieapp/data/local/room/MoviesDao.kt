package com.affan.movieapp.data.local.room


import androidx.room.*

@Dao
interface MoviesDao {

    @Query("SELECT * FROM FavoriteMovies")
    suspend fun getAllFavorit() : List<FavoriteMovies>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(cinema : FavoriteMovies)

    @Delete
    suspend fun deleteFavorite(cinema: FavoriteMovies)

}