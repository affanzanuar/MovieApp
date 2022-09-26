package com.affan.movieapp.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Favorite::class],
    version = 1,
    exportSchema = false
)
abstract class FavoriteDatabase : RoomDatabase() {

    abstract fun favoriteDao() : FavoriteDao

    companion object {
        @Volatile
        private var DB_INSTANCE : FavoriteDatabase? = null

        fun getFavoriteDBInstance(context: Context) : FavoriteDatabase {
            if(DB_INSTANCE == null) {
                DB_INSTANCE = Room.databaseBuilder(context.applicationContext,
                    FavoriteDatabase::class.java,
                    "favorite_db"
                )
                    .allowMainThreadQueries()
                    .build()
            }
            return DB_INSTANCE!!
        }
    }
}