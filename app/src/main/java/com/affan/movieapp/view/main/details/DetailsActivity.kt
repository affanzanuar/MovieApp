package com.affan.movieapp.view.main.details

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.view.ViewGroup
import android.webkit.URLUtil
import android.widget.FrameLayout
import android.widget.MediaController
import android.widget.Toast
import com.affan.movieapp.databinding.ActivityDetailsBinding
import com.affan.movieapp.model.MoviesOrSeries
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

        mediaController.setPadding(0,0,0,1050)

        binding.vvTrailer.setMediaController(mediaController)

        binding.vvTrailer.setOnCompletionListener {
            binding.vvTrailer.seekTo(0)
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
        binding.tvReleaseDate.text = getDataMoviesOrSeries().releaseDate
        binding.tvOriginalLanguage.text = getDataMoviesOrSeries().originalLanguage
        binding.tvVoteCount.text = getDataMoviesOrSeries().voteCount.toString()
        binding.tvRatingResult.text = getDataMoviesOrSeries().moviesOrSeriesRating
        binding.tvDescriptionMS.text = getDataMoviesOrSeries().moviesOrSeriesDescription

        if (!getDataMoviesOrSeries().moviesOrSeriesIsAdult){
            binding.tvIsAdult.visibility = View.GONE
        }
    }

    companion object{
        private const val VIDEO_SAMPLE = "https://rr3---sn-4g5lznl6.googlevideo.com/videoplayback?expire=1663362789&ei=hZIkY8mXAs-AgAfKjYbABw&ip=45.88.97.238&id=o-AF3eTc8OJTlagAmcpDDMhSSHHpQe0f6w1aOWN-VqV49C&itag=22&source=youtube&requiressl=yes&mh=HO&mm=31%2C26&mn=sn-4g5lznl6%2Csn-5hne6nsd&ms=au%2Conr&mv=m&mvi=3&pl=27&initcwndbps=605000&spc=yR2vp228cIqoppWryQ1rOLVtIyCkVDg&vprv=1&mime=video%2Fmp4&ns=KT7Fy9sX-t1CvMySOhwD5W8I&cnr=14&ratebypass=yes&dur=162.261&lmt=1661415550946038&mt=1663340704&fvip=3&fexp=24001373%2C24007246&beids=24239126&c=WEB&rbqsm=fr&txp=4532434&n=Fx4r6rXMdhxn6g&sparams=expire%2Cei%2Cip%2Cid%2Citag%2Csource%2Crequiressl%2Cspc%2Cvprv%2Cmime%2Cns%2Ccnr%2Cratebypass%2Cdur%2Clmt&sig=AOq0QJ8wRQIgA71nmsTJL2zgKRrrYCzC-solppTvo0y9LRoK7qw7-wICIQC7RIHKrpgCCslE5G59ul_5BiRoOplg-hgAQ3KxlWnTqg%3D%3D&lsparams=mh%2Cmm%2Cmn%2Cms%2Cmv%2Cmvi%2Cpl%2Cinitcwndbps&lsig=AG3C_xAwRQIgDqzH9P2DC74GN4n1_hHdV5TApi4dHyRFHzzVMr9I4coCIQCtZWGiWzmBiKFzPKsw6vbRAd8LH36OqU-H-U8_fl-fMg%3D%3D&title=FALL%20Official%20Trailer%20(2022)"
        private const val PLAYBACK_TIME = "play_time"
    }
}