package com.affan.movieapp.data.local.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface FavoriteDao {
    @Query("SELECT * FROM Favorite")
    suspend fun getAllFavorite () : LiveData<List<Favorite>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite (id : Int) : Favorite

    @Delete
    suspend fun deleteFavorite (id: Int) : Favorite
}