package com.affan.movieapp.data.local.room


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.affan.movieapp.model.favorite.FavoriteMovies


@Database(
    entities =[FavoriteMovies::class],
    version = 1
)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun moviesDao() : MoviesDao

    companion object {
        private var INSTANCE : MovieDatabase? = null
        fun getInstance (context: Context) : MovieDatabase{
            synchronized(this){
                return INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    MovieDatabase::class.java,
                    "student-db"
                ).build().also {
                    INSTANCE = it
                }
            }
        }
    }

}