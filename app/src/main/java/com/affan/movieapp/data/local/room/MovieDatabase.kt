package com.affan.movieapp.data.local.room

//import android.arch.persistence.room.Database
//import android.arch.persistence.room.RoomDatabase
//import android.content.Context
//import com.affan.movieapp.model.MoviesOrSeries


//@Database(
//    entities =[MoviesOrSeries::class],
//    version = 1
//)
//abstract class MovieDatabase : RoomDatabase() {
//
//    abstract fun moviesDao() : MoviesDao
//
//    companion object {
////        fun getInstance (context: Context) : MovieDatabase{
////            synchronized(this){
////                return INSTANCE ?: Room.databaseBuilder(
////                    context.applicationContext,
////                    StudentDatabase::class.java,
////                    "student-db"
////                ).build().also {
////                    INSTANCE = it
////                }
////            }
//////        }
//    }
//
//}