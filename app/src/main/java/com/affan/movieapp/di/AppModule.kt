package com.affan.movieapp.di

import android.app.Application
import androidx.room.Room
import com.affan.movieapp.data.DataSource
import com.affan.movieapp.data.DataSourceFactory
import com.affan.movieapp.data.local.LocalDataSource
import com.affan.movieapp.data.local.room.FavoriteDao
import com.affan.movieapp.data.local.room.FavoriteDatabase
import com.affan.movieapp.data.remote.RemoteDataSource
import com.affan.movieapp.domain.Repository
import com.affan.movieapp.domain.RepositoryImp
import com.affan.movieapp.network.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    //------------PROVIDE DATA SOURCE--------------
    @Provides
    @Singleton
    fun getRepository(
        dataSourceFactory : DataSourceFactory
    ) : Repository {
        return RepositoryImp(
            dataSourceFactory
        )
    }

    @Provides
    @Singleton
    fun getDataSource(
        remoteDataSource : RemoteDataSource,
        localDataSource : LocalDataSource
    ) : DataSource {
        return DataSourceFactory(
            remoteDataSource,
            localDataSource
        )
    }


    //-----------------LOCAL----------------
    @Provides
    @Singleton
    fun provideLocalDataSource (favoriteDao: FavoriteDao) : LocalDataSource{
        return LocalDataSource(
            favoriteDao
        )
    }

    @Provides
    @Singleton
    fun getFavoriteDao(favoriteDatabase : FavoriteDatabase) : FavoriteDao{
        return favoriteDatabase.favoriteDao()
    }

//    @Provides
//    @Singleton
//    fun getFavoriteDatabase(context: Application) : FavoriteDatabase{
//        return FavoriteDatabase.getFavoriteDBInstance(context)
//    }
    @Provides
    @Singleton
    fun provideFavoriteDatabase (
        context: Application
    ) : FavoriteDatabase {
        return Room.databaseBuilder(
            context.applicationContext,FavoriteDatabase::class.java,"favorite_db"
        )
            .allowMainThreadQueries()
            .build()
    }





    //-----------------REMOTE----------------

    @Provides
    @Singleton
    fun provideRemoteDataSource (apiService: ApiService) : DataSource{
        return RemoteDataSource(
            apiService
        )
    }


    @Provides
    @Singleton
    fun getApiService(retrofit : Retrofit) : ApiService{
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun getRetrofitInstance (client : OkHttpClient) : Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun getOkHttpClient ( httpLoggingInterceptor: Interceptor) : OkHttpClient {
        return OkHttpClient
            .Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun getHttpLoggingInterceptor () : Interceptor {
        return HttpLoggingInterceptor()
    }

}