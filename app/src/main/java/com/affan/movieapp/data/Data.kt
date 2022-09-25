package com.affan.movieapp.data

import com.affan.movieapp.R
import com.affan.movieapp.model.MoviesOrSeries
import com.affan.movieapp.main.movies.MoviesData
import com.affan.movieapp.main.series.SeriesData
import java.time.LocalDate
import java.time.format.DateTimeFormatter

object Data {

    const val apiKey = "b19b7218066efbe56b3d9d35f71e509a"
    const val language = "en-US"
    const val sortBy = "popularity.desc"
    const val page = 1
    val releaseDateGte: String = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDate.now())

    private val currentYear0 = releaseDateGte[0]
    private val currentYear1 = releaseDateGte[1]
    private val currentYear2 = releaseDateGte[2]
    private val currentYear3 = releaseDateGte[3]

    private val currentYear = "$currentYear0$currentYear1$currentYear2$currentYear3".toInt()
    private val twoYearsFromNow = currentYear + 2

    val releaseDateLte : String = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(
        LocalDate.ofYearDay(twoYearsFromNow,12))

    const val monetizationTypes = "flatrate"

    val itemTopMovies : ArrayList<MoviesOrSeries> = arrayListOf(
        MoviesOrSeries(
            0,
        ),
        MoviesOrSeries(
            1,
        ),
        MoviesOrSeries(
            2,
        ),
        MoviesOrSeries(
            3,
        ),
        MoviesOrSeries(
            4,
        )
    )

    val itemMovies : ArrayList<MoviesData> = arrayListOf(
        MoviesData("TEST1", "DESC1", R.drawable.postertest),
        MoviesData("TEST1", "DESC1", R.drawable.postertest),
        MoviesData("TEST1", "DESC1", R.drawable.postertest),
        MoviesData("TEST1", "DESC1", R.drawable.postertest),
        MoviesData("TEST1", "DESC1", R.drawable.postertest),
        MoviesData("TEST1", "DESC1", R.drawable.postertest),
        MoviesData("TEST1", "DESC1", R.drawable.postertest),
        MoviesData("TEST1", "DESC1", R.drawable.postertest),
        MoviesData("TEST1", "DESC1", R.drawable.postertest),
        MoviesData("TEST1", "DESC1", R.drawable.postertest),
        MoviesData("TEST1", "DESC1", R.drawable.postertest),
        MoviesData("TEST1", "DESC1", R.drawable.postertest),
        MoviesData("TEST1", "DESC1", R.drawable.postertest),
        MoviesData("TEST1", "DESC1", R.drawable.postertest),
    )

    val itemSeries : ArrayList<SeriesData> = arrayListOf(
        SeriesData("TEST1", "DESC1", R.drawable.postertest),
        SeriesData("TEST1", "DESC1", R.drawable.postertest),
        SeriesData("TEST1", "DESC1", R.drawable.postertest),
        SeriesData("TEST1", "DESC1", R.drawable.postertest),
        SeriesData("TEST1", "DESC1", R.drawable.postertest),
        SeriesData("TEST1", "DESC1", R.drawable.postertest),
        SeriesData("TEST1", "DESC1", R.drawable.postertest),
        SeriesData("TEST1", "DESC1", R.drawable.postertest),
        SeriesData("TEST1", "DESC1", R.drawable.postertest),
        SeriesData("TEST1", "DESC1", R.drawable.postertest),
        SeriesData("TEST1", "DESC1", R.drawable.postertest),
        SeriesData("TEST1", "DESC1", R.drawable.postertest),
        SeriesData("TEST1", "DESC1", R.drawable.postertest),
        SeriesData("TEST1", "DESC1", R.drawable.postertest),
        SeriesData("TEST1", "DESC1", R.drawable.postertest),
        SeriesData("TEST1", "DESC1", R.drawable.postertest)
    )

}