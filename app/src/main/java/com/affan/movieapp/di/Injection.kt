package com.affan.movieapp.di

import android.content.Context
import androidx.room.Room
import com.affan.movieapp.data.DataSource
import com.affan.movieapp.data.local.LocalDataSource
import com.affan.movieapp.data.local.room.FavoriteDao
import com.affan.movieapp.data.local.room.FavoriteDatabase
import com.affan.movieapp.data.remote.RemoteDataSource
import com.affan.movieapp.domain.Repository
import com.affan.movieapp.domain.RepositoryImp
import com.affan.movieapp.network.ApiService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//Remote
//fun provideRepository(
//    remoteDataSource : DataSource,
//    localDataSource : DataSource
//) : Repository{
//    return RepositoryImp(
//        remoteDataSource,
//        localDataSource
//    )
//}

fun provideRemoteDataSource (apiService: ApiService) : DataSource{
    return RemoteDataSource(
        apiService
    )
}

fun provideApiService (retrofit: Retrofit) : ApiService {
    return retrofit.create(ApiService::class.java)
}

fun provideRetrofit (
    gsonConverterFactory: Converter.Factory,
    okHttpClient: OkHttpClient
) : Retrofit {
    return Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .client(okHttpClient)
        .addConverterFactory(gsonConverterFactory)
        .build()
}

fun provideGsonConverterFactory () : Converter.Factory {
    return GsonConverterFactory.create()
}

fun provideOkHttpClient (
    httpLoggingInterceptor : Interceptor
) : OkHttpClient {
    return OkHttpClient
        .Builder()
        .addInterceptor(httpLoggingInterceptor)
        .build()
}

fun provideHttpLoggingInterceptor() : Interceptor {
    return HttpLoggingInterceptor()
}






//Local

fun provideLocalDataSource (favoriteDao: FavoriteDao) : DataSource{
    return LocalDataSource(
        favoriteDao
    )
}

fun provideFavoriteDao (
    favoriteDatabase: FavoriteDatabase
) : FavoriteDao {
    return favoriteDatabase.favoriteDao()
}

fun provideFavoriteDatabase (
    context: Context
) : FavoriteDatabase {
    return Room.databaseBuilder(
        context.applicationContext,FavoriteDatabase::class.java,"favorite_db"
    ).build()
}

