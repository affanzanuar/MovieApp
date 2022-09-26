package com.affan.movieapp.data

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


}