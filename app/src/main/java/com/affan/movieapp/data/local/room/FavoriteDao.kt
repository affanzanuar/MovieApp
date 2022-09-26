package com.affan.movieapp.data.local.room

import androidx.room.*

@Dao
interface FavoriteDao {
    @Query("SELECT * FROM Favorite")
    suspend fun getAllFavorite () : List<Favorite>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite (id : Int) : Favorite

    @Delete
    suspend fun deleteFavorite (id: Int) : Favorite
}