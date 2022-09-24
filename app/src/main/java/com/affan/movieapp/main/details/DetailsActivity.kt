package com.affan.movieapp.main.details

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
import com.affan.movieapp.main.home.view.HomeFragment
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

        val id = intent.getIntExtra(HomeFragment.ID, 0)
        val category = intent.getStringExtra(HomeFragment.CATEGORY).orEmpty()

        Log.d("cekid", id.toString())
        Log.d("cekcategory", category)

        detailsViewModel = ViewModelProvider(
            this,
            DetailsModelFactory(
                ApiClient.instance,
            )
        ).get(DetailsViewModel::class.java)

        observeLiveData()
        detailsViewModel.getDetailsMovie(id, category)

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
                .placeholder(R.drawable.ic_default_poster)
                .into(binding.ivPosterDetail)

            Glide.with(this)
                .load(BASE_URL + data.backdropPath)
                .placeholder(R.drawable.ic_default_backdrop)
                .into(binding.ivBackdropDetails)

            binding.tvVoteCount.text = data.voteCount.toString()

            val voteRating = data.voteAverage

            val ratingTwoComa = String.format("%.1f", voteRating)

            binding.tvRatingResult.text = ratingTwoComa
            binding.tvDescriptionMS.text = data.overview
//            binding.tvOriginalLanguage.text = data.originalLanguage


            binding.tvTitleDetail.text = data.title

            var currentDate = data.releaseDate

            val year0 = currentDate?.get(0)
            val year1 = currentDate?.get(1)
            val year2 = currentDate?.get(2)
            val year3 = currentDate?.get(3)
            val year = "$year0$year1$year2$year3"

            val month0 = currentDate?.get(5)
            val month1 = currentDate?.get(6)
            val month = "$month0$month1"

            val date0 = currentDate?.get(8)
            val date1 = currentDate?.get(9)
            val date = "$date0$date1"

            binding.tvReleaseDate.text = "$date-$month-$year"

//            feature to full name language
            val languageName = data.spokenLanguages?.map { it?.englishName }

            if (languageName!!.isNotEmpty()) {
                binding.tvOriginalLanguage.text = languageName[0]
            } else {
                binding.tvOriginalLanguage.text = "No Language"
            }

            val genreName = data.genres?.map { it?.name }?.toTypedArray()
            val sbGenre = StringBuilder()
            for (i in 0 until (genreName?.size ?: 0)) {
                sbGenre.append(genreName?.get(i).toString() + "\n")

                if (!data.adult!!) {
                    binding.tvIsAdult.visibility = View.GONE
                }
            }
            binding.tvGenre.text = (sbGenre.toString())

        }
        detailsViewModel.error.observe(this) { error ->
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
            Log.d("asdasd",error)
        }
    }


    companion object {
        private const val VIDEO_SAMPLE =
            "https://joy.videvo.net/videvo_files/video/free/2014-07/large_watermarked/Run_5_wo_metadata_h264420_720p_UHQ_preview.mp4"
        private const val PLAYBACK_TIME = "play_time"
        private const val BASE_URL = "https://image.tmdb.org/t/p/w500"
    }


}