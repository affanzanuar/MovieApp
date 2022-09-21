package com.affan.movieapp.data

import com.affan.movieapp.R
import com.affan.movieapp.model.MoviesOrSeries
import com.affan.movieapp.view.main.movies.MoviesData
import com.affan.movieapp.view.main.series.SeriesData
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
            "KKN Di Desa Penari",
            R.drawable.poster_kkn_di_desa_penari,
            R.drawable.kkn_di_desa_penari,
            "Horror",
            "5.8",
            true,
            "Enam mahasiswa yang sedang melakukan kuliah kerja nyata di desa terpencil mendapatkan terror dari penaris mistis. Ternyata, salah satu dari mereka melanggar aturan paling fatal di desa tersebut.",
            "2022-06-20",
            "in",
            402
        ),
        MoviesOrSeries(
            1,
            "She-Hulk: Attorney at Law",
            R.drawable.poster_she_hulk,
            R.drawable.she_hulk,
            "Sci-fi & Fantasy",
            "7.3",
            false,
            "Jennifer Walters menavigasi kehidupan rumit seorang pengacara tunggal berusia 30-an yang juga merupakan raksasa superpowered hijau 6-kaki-7-inci.",
            "2022-06-11",
            "en",
            1598
        ),
        MoviesOrSeries(
            2,
            "Lightyear",
            R.drawable.poster_lightyear,
            R.drawable.lightyear,
            "Animasi",
            "7.2",
            false,
            "Space Ranger legendaris Buzz Lightyear memulai petualangan intergalaksi bersama sekelompok rekrutan ambisius dan pendamping robotnya Sox.",
            "2022-06-11",
            "en",
            1598
        ),
        MoviesOrSeries(
            3,
            "Doctor Strange in the Multiverse of Madness",
            R.drawable.poster_doctor_strange,
            R.drawable.doctor_strange,
            "Fantasi, Aksi, Petualangan",
            "7.5",
            false,
            "Sinopsis Doctor Strange in the Multiverse of Madness mengisahkan Stephen Strange yang kedatangan tamu dari semesta lain, yaitu America Chavez. Kehadiran America ke semesta Strange yang berada di universe utama MCU adalah untuk meminta bantuan sang superhero",
            "2022-06-11",
            "en",
            1598
        ),
        MoviesOrSeries(
            4,
            "Iron Fist:Season 2",
            R.drawable.poster_iron_fist,
            R.drawable.iron_fist,
            "Aksi & Petualangan, Drama, Sci-fi & Fantasy",
            "6.6",
            true,
            "Danny Rand muncul kembali setelah dinyatakan tewas 15 tahun lalu. Sekarang, dengan kekuatan Iron Fist, ia berusaha merebut kembali masa lalunya dan memenuhi takdirnya.",
            "2022-06-11",
            "en",
            1598
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