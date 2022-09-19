package com.affan.movieapp.view.main.details

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.webkit.URLUtil
import android.widget.MediaController
import com.affan.movieapp.R
import com.affan.movieapp.databinding.ActivityDetailsBinding
import com.affan.movieapp.model.movie.Movie
import com.affan.movieapp.model.trending.MoviesSeries
import com.affan.movieapp.view.main.home.HomeFragment
import com.bumptech.glide.Glide

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding

    private var currentPosition = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setDataToDetail()

        binding.ivBack.setOnClickListener {
            finish()
        }

        if (savedInstanceState != null){
            currentPosition = savedInstanceState.getInt(PLAYBACK_TIME)
        }
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

    private fun getDataMoviesOrSeries(): Movie {
        return intent.getParcelableExtra<Movie>(HomeFragment.EXTRA_DATA_MS)
                as Movie
    }

    private fun getDataTopMoviesOrSeries() : MoviesSeries {
        return intent.getParcelableExtra<MoviesSeries>(HomeFragment.EXTRA_DATA_MS)
                as MoviesSeries
    }

    private fun setDataToDetail(){
        Glide.with(this)
            .load(getDataMoviesOrSeries().loadPoster())
            .placeholder(R.drawable.ic_default_top_movies)
            .into(binding.ivPosterDetail)

        Glide.with(this)
            .load(getDataMoviesOrSeries().loadBackdrop())
            .into(binding.ivBackdropDetails)

        binding.tvTitleDetail.text = getDataMoviesOrSeries().title ?: getDataTopMoviesOrSeries().name
        binding.tvGenre.text = getDataMoviesOrSeries().genreIds.toString()
        binding.tvReleaseDate.text = getDataMoviesOrSeries().releaseDate
        binding.tvOriginalLanguage.text = getDataMoviesOrSeries().originalLanguage
        binding.tvVoteCount.text = getDataMoviesOrSeries().voteCount.toString()
        binding.tvRatingResult.text = getDataMoviesOrSeries().voteAverage.toString()
        binding.tvDescriptionMS.text = getDataMoviesOrSeries().overview

        if (!getDataMoviesOrSeries().adult!!){
            binding.tvIsAdult.visibility = View.GONE
        }
    }

    companion object{
        private const val VIDEO_SAMPLE = "https://joy.videvo.net/videvo_files/video/free/2014-07/large_watermarked/Run_5_wo_metadata_h264420_720p_UHQ_preview.mp4"
        private const val PLAYBACK_TIME = "play_time"
    }
}