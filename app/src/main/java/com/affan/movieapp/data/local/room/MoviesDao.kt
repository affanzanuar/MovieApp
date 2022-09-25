package com.affan.movieapp.data.local.room

//import android.arch.persistence.room.*
//import com.affan.movieapp.model.MoviesOrSeries

//@Dao
//interface MoviesDao {
//
//    @Query("SELECT * FROM MoviesOrSeries")
//    suspend fun getAllFavorit(id : Int) : List<MoviesOrSeries>
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertFavorite(cinema : MoviesOrSeries) : Long
//
//    @Delete
//    suspend fun deleteFavorite(id: Int) : MoviesOrSeries
//
//}