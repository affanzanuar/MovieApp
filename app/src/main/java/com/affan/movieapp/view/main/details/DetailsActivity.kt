package com.affan.movieapp.view.main.details

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.webkit.URLUtil
import android.widget.MediaController
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

        mediaController.setPadding(0,-1000,0,0)

        mediaController.setAnchorView(binding.flDummy)
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
        private const val VIDEO_SAMPLE = "https://rr5---sn-npoe7nsk.googlevideo.com/videoplayback?expire=1663430597&ei=ZZslY7LlIMqn0_wP0f6Y6AY&ip=216.151.191.183&id=o-ACRCEp5ZojUwv3gAsfNhhywaiNLrAueXt5EMQZIIRsbm&itag=18&source=youtube&requiressl=yes&bui=0&spc=yR2vp8Tj4huMzJMYarjHULTlxMNa6uI&vprv=1&mime=video%2Fmp4&ns=T-Z8TxbN1bvBbrUTqWdOpoEI&gir=yes&clen=10247860&ratebypass=yes&dur=136.597&lmt=1648744409420674&fexp=9420244,24001373,24007246&c=WEB&rbqsm=fr&txp=4530434&n=pfdV-DE2uWzW1Q&sparams=expire%2Cei%2Cip%2Cid%2Citag%2Csource%2Crequiressl%2Cbui%2Cspc%2Cvprv%2Cmime%2Cns%2Cgir%2Cclen%2Cratebypass%2Cdur%2Clmt&sig=AOq0QJ8wRAIgJb98z5USI0JCtpOzhDLzE04X983nSghyhABIs68bx4ICIBWRUXRT-9VxGw_8g41iqFCFY4zY-Yy8MRLBLa2QryY1&rm=sn-p5qeey7l&req_id=bfc479f20a6fa3ee&redirect_counter=2&cm2rm=sn-2uuxa3vh-ngbz7l&cms_redirect=yes&cmsv=e&ipbypass=yes&mh=L0&mip=2001:448a:4000:37d4:c0d7:1c65:3866:ff7f&mm=29&mn=sn-npoe7nsk&ms=rdu&mt=1663408861&mv=m&mvi=5&pl=52&lsparams=ipbypass,mh,mip,mm,mn,ms,mv,mvi,pl&lsig=AG3C_xAwRAIgJTRebRgBWbTLJfPUu7bubBWXkV6bbfeHMSjnwLs6KucCIA20kRWr9h1m48WeOzntCyO0efV_-s5A3AxXO6V36pmJ"
        private const val PLAYBACK_TIME = "play_time"
    }
}