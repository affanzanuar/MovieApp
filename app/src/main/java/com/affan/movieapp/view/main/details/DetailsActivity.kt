package com.affan.movieapp.view.main.details

import android.net.Uri
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.webkit.URLUtil
import android.widget.MediaController
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.affan.movieapp.R
import com.affan.movieapp.databinding.ActivityDetailsBinding
import com.affan.movieapp.model.movie.Movie
import com.affan.movieapp.model.trending.Trending
import com.affan.movieapp.network.ApiClient
import com.affan.movieapp.view.main.home.HomeFragment
import com.bumptech.glide.Glide

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding

    private lateinit var detailsViewModel: DetailsViewModel

    private var currentPosition = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        setDataToDetail()

        binding.ivBack.setOnClickListener {
            finish()
        }

        if (savedInstanceState != null) {
            currentPosition = savedInstanceState.getInt(PLAYBACK_TIME)
        }

        val id = intent.getIntExtra("id", 0)
        val category = intent.getStringExtra("category").orEmpty()

        Log.d("cekid", id.toString())
        Log.d("cekcategory", category)

        detailsViewModel = ViewModelProvider(
            this,
            DetailsModelFactory(
                ApiClient.instance,
            )
        ).get(DetailsViewModel::class.java)

        observeLiveData()
        detailsViewModel.getDetailsMovie(id,category)

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

    private fun getMedia(mediaName: String): Uri {
        return if (URLUtil.isValidUrl(mediaName)) {
            Uri.parse(mediaName)
        } else {
            Uri.parse(
                "android.resource://$packageName/raw$mediaName"
            )
        }
    }

    private fun initializePlayer() {

        binding.pbBuffering.visibility = View.VISIBLE
        val videoUri = getMedia(VIDEO_SAMPLE)
        binding.vvTrailer.setVideoURI(videoUri)

        val mediaController = MediaController(this)

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

        mediaController.setPadding(0, 0, 0, 0)

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

    private fun getDataTopMoviesOrSeries(): Trending {
        return intent.getParcelableExtra<Trending>(HomeFragment.EXTRA_DATA_MS)
                as Trending
    }

    private fun observeLiveData() {
        detailsViewModel.loading.observe(this) { isLoading ->
            // TODO:
        }

        detailsViewModel.detailResponse.observe(this) { data ->
            Glide.with(this)
                .load(BASE_URL + data.posterPath)
                .placeholder(R.drawable.ic_default_top_movies)
                .into(binding.ivPosterDetail)

            Glide.with(this)
                .load(BASE_URL + data.backdropPath)
                .into(binding.ivBackdropDetails)

            binding.tvVoteCount.text = data.voteCount.toString()
            binding.tvRatingResult.text = data.voteAverage.toString()
            binding.tvDescriptionMS.text = data.overview
            binding.tvOriginalLanguage.text = data.originalLanguage


            binding.tvTitleDetail.text = data.title
            binding.tvReleaseDate.text = data.releaseDate

//            feature to full name language
//            val languageName = data.spokenLanguages?.map { it?.englishName }
//            binding.tvOriginalLanguage.text = languageName?.get(0) ?: "null"

            val genreName = data.genres?.map { it?.name }?.toTypedArray()
            val sbGenre = StringBuilder()
            for (i in 0 until (genreName?.size ?: 0)) {
                sbGenre.append(genreName?.get(i).toString() + "\n")
            }
            binding.tvGenre.text = (sbGenre.toString())

        }
        detailsViewModel.error.observe(this) { error ->
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
        }
    }


    companion object {
        private const val VIDEO_SAMPLE =
            "https://joy.videvo.net/videvo_files/video/free/2014-07/large_watermarked/Run_5_wo_metadata_h264420_720p_UHQ_preview.mp4"
        private const val PLAYBACK_TIME = "play_time"
        private const val BASE_URL = "https://image.tmdb.org/t/p/w500"
    }


}

