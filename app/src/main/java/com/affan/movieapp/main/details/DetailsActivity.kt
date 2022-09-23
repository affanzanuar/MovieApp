package com.affan.movieapp.main.details

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.webkit.URLUtil
import android.widget.MediaController
import com.affan.movieapp.R
import com.affan.movieapp.databinding.ActivityDetailsBinding
import com.affan.movieapp.model.comingsoon.ComingSoon
import com.affan.movieapp.model.movie.Movie
import com.affan.movieapp.model.series.Series
import com.affan.movieapp.model.trending.Trending
import com.affan.movieapp.main.home.HomeFragment
import com.bumptech.glide.Glide

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding

    private var currentPosition = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)



        if (getCategory()=="trending"){
            setTrendingToDetail()
        } else if (getCategory()=="movies") {
            setMoviesToDetail()
        } else if (getCategory()=="series"){
            setSeriesToDetail()
        } else {
            setComingSoonToDetail()
        }

        binding.ivBack.setOnClickListener {
            finish()
        }

        if (savedInstanceState != null){
            currentPosition = savedInstanceState.getInt(PLAYBACK_TIME)
        }
    }

    private fun getCategory() : String{
        return intent.getStringExtra(HomeFragment.CATEGORY)!!
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        outState.putInt(PLAYBACK_TIME, binding.vvTrailer.currentPosition)
    }

    override fun onPause() {
        super.onPause()
        binding.vvTrailer.pause()
    }

    override fun onStart() {
        super.onStart()
        initializePlayer()
    }

    override fun onStop() {
        super.onStop()
        binding.vvTrailer.stopPlayback()
    }

    private fun getMedia (mediaName : String) : Uri {
        return if (URLUtil.isValidUrl(mediaName)) {
            Uri.parse(mediaName)
        } else {
            Uri.parse(
                "android.resource://$packageName/raw$mediaName"
            )
        }
    }

    private fun initializePlayer(){

        binding.pbBuffering.visibility = View.VISIBLE
        val videoUri = getMedia(VIDEO_SAMPLE)
        binding.vvTrailer.setVideoURI(videoUri)

        val mediaController = MediaController(this )

        binding.vvTrailer.setOnPreparedListener {

            binding.pbBuffering.visibility = View.GONE
            binding.ivBackdropDetails.visibility = View.GONE

            if (currentPosition > 0) {
                binding.vvTrailer.seekTo(currentPosition)
            } else {
                binding.vvTrailer.seekTo(1)
            }
            binding.vvTrailer.start()

        }

        mediaController.setPadding(0,0,0,0)

        mediaController.setAnchorView(binding.flDummy)
        binding.vvTrailer.setMediaController(mediaController)

        binding.vvTrailer.setOnCompletionListener {
            binding.vvTrailer.seekTo(0)
        }
    }

    private fun getDataTrending() : Trending {
        return intent.getParcelableExtra<Trending>(HomeFragment.EXTRA_DATA_MS)
                as Trending
    }

    private fun getDataMovies(): Movie {
        return intent.getParcelableExtra<Movie>(HomeFragment.EXTRA_DATA_MS)
                as Movie
    }

    private fun getDataSeries(): Series {
        return intent.getParcelableExtra<Series>(HomeFragment.EXTRA_DATA_MS)
                as Series
    }

    private fun getDataComingSoon(): ComingSoon {
        return intent.getParcelableExtra<ComingSoon>(HomeFragment.EXTRA_DATA_MS)
                as ComingSoon
    }

    private fun setTrendingToDetail(){
        Glide.with(this)
            .load(getDataTrending().loadPoster())
            .placeholder(R.drawable.ic_default_poster)
            .into(binding.ivPosterDetail)

        Glide.with(this)
            .load(getDataTrending().loadBackdrop())
            .placeholder(R.drawable.ic_default_top_movies)
            .into(binding.ivBackdropDetails)

        binding.tvTitleDetail.text = getDataTrending().title ?: getDataTrending().name
        binding.tvGenre.text = getDataTrending().genreIds.toString()
        binding.tvReleaseDate.text = getDataTrending().releaseDate ?: getDataTrending().firstAirDate
        binding.tvOriginalLanguage.text = getDataTrending().originalLanguage
        binding.tvVoteCount.text = getDataTrending().voteCount.toString()
        binding.tvRatingResult.text = getDataTrending().voteAverage.toString()
        binding.tvDescriptionMS.text = getDataTrending().overview

        if (!getDataTrending().adult!!){
            binding.tvIsAdult.visibility = View.GONE
        }
    }

    private fun setMoviesToDetail(){
        Glide.with(this)
            .load(getDataMovies().loadPoster())
            .placeholder(R.drawable.ic_default_poster)
            .into(binding.ivPosterDetail)

        Glide.with(this)
            .load(getDataMovies().loadBackdrop())
            .placeholder(R.drawable.ic_default_top_movies)
            .into(binding.ivBackdropDetails)

        binding.tvTitleDetail.text = getDataMovies().title
        binding.tvGenre.text = getDataMovies().genreIds.toString()
        binding.tvReleaseDate.text = getDataMovies().releaseDate
        binding.tvOriginalLanguage.text = getDataMovies().originalLanguage
        binding.tvVoteCount.text = getDataMovies().voteCount.toString()
        binding.tvRatingResult.text = getDataMovies().voteAverage.toString()
        binding.tvDescriptionMS.text = getDataMovies().overview

        if (!getDataMovies().adult!!){
            binding.tvIsAdult.visibility = View.GONE
        }
    }

    private fun setSeriesToDetail(){
        Glide.with(this)
            .load(getDataSeries().loadPoster())
            .placeholder(R.drawable.ic_default_poster)
            .into(binding.ivPosterDetail)

        Glide.with(this)
            .load(getDataSeries().loadBackdrop())
            .placeholder(R.drawable.ic_default_top_movies)
            .into(binding.ivBackdropDetails)

        binding.tvTitleDetail.text = getDataSeries().name
        binding.tvGenre.text = getDataSeries().genreIds.toString()
        binding.tvReleaseDate.text = getDataSeries().firstAirDate
        binding.tvOriginalLanguage.text = getDataSeries().originalLanguage
        binding.tvVoteCount.text = getDataSeries().voteCount.toString()
        binding.tvRatingResult.text = getDataSeries().voteAverage.toString()
        binding.tvDescriptionMS.text = getDataSeries().overview

        binding.tvIsAdult.visibility = View.GONE

    }

    private fun setComingSoonToDetail(){
        Glide.with(this)
            .load(getDataComingSoon().loadPoster())
            .placeholder(R.drawable.ic_default_poster)
            .into(binding.ivPosterDetail)

        Glide.with(this)
            .load(getDataComingSoon().loadBackdrop())
            .placeholder(R.drawable.ic_default_top_movies)
            .into(binding.ivBackdropDetails)

        binding.tvTitleDetail.text = getDataComingSoon().title
        binding.tvGenre.text = getDataComingSoon().genreIds.toString()
        binding.tvReleaseDate.text = getDataComingSoon().releaseDate
        binding.tvOriginalLanguage.text = getDataComingSoon().originalLanguage
        binding.tvVoteCount.text = getDataComingSoon().voteCount.toString()
        binding.tvRatingResult.text = getDataComingSoon().voteAverage.toString()
        binding.tvDescriptionMS.text = getDataComingSoon().overview

        if (!getDataComingSoon().adult!!){
            binding.tvIsAdult.visibility = View.GONE
        }
    }

    companion object{
        private const val VIDEO_SAMPLE = "https://joy.videvo.net/videvo_files/video/free/2014-07/large_watermarked/Run_5_wo_metadata_h264420_720p_UHQ_preview.mp4"
        private const val PLAYBACK_TIME = "play_time"
    }
}