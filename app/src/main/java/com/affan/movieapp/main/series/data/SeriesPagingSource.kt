package com.affan.movieapp.main.series.data

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.affan.movieapp.data.Data
import com.affan.movieapp.model.series.Series
import com.affan.movieapp.network.ApiService
import okio.IOException
import retrofit2.HttpException

private const val MOVIE_API_STARTING_PAGE_INDEX = 1


class SeriesPagingSource(
    private val service: ApiService
) : PagingSource<Int, Series>() {
    override fun getRefreshKey(state: PagingState<Int, Series>): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Series> {

        val position = params.key ?: MOVIE_API_STARTING_PAGE_INDEX

        return try {
            val series = service.getMostPopularSeries3(
                Data.apiKey,
                position
            ).series
            LoadResult.Page(
                data = series,
                prevKey = if (position == MOVIE_API_STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (series.isEmpty()) null else position + 1

            )
        } catch (e: IOException) {
            Log.d("pagingSource", "load: ${e.message}")
            LoadResult.Error(e)
        } catch (e: HttpException) {
            Log.d("pagingSource", "load: ${e.message}")
            LoadResult.Error(e)
        }
    }

}