package com.affan.movieapp.view.main.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.affan.movieapp.databinding.ActivityDetailsBinding
import com.affan.movieapp.model.MoviesOrSeries
import com.affan.movieapp.view.main.home.HomeFragment
import com.bumptech.glide.Glide

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setDataToDetail()

        binding.ivBack.setOnClickListener {
            finish()
        }
    }

    private fun getDataMoviesOrSeries(): MoviesOrSeries {
        return intent.getParcelableExtra<MoviesOrSeries>(HomeFragment.EXTRA_DATA_MS)
                as MoviesOrSeries
    }

    private fun setDataToDetail(){
        Glide.with(this)
            .load(getDataMoviesOrSeries().moviesOrSeriesPoster)
            .into(binding.ivPosterDetail)

        Glide.with(this)
            .load(getDataMoviesOrSeries().moviesOrSeriesBackDrop)
            .into(binding.ivBackdropDetails)

        binding.tvTitleDetail.text = getDataMoviesOrSeries().moviesOrSeriesTitle
        binding.tvGenre.text = getDataMoviesOrSeries().moviesOrSeriesGenre
        binding.tvRatingResult.text = getDataMoviesOrSeries().moviesOrSeriesRating
        binding.tvDescriptionMS.text = getDataMoviesOrSeries().moviesOrSeriesDescription

        if (!getDataMoviesOrSeries().moviesOrSeriesIsAdult){
            binding.tvIsAdult.visibility = View.GONE
        }
    }
}