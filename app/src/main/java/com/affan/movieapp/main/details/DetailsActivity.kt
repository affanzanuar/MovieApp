package com.affan.movieapp.main.details

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.affan.movieapp.R
import com.affan.movieapp.model.FavoriteMovies
import com.affan.movieapp.databinding.ActivityDetailsBinding
import com.affan.movieapp.di.ViewModelFactory
import com.affan.movieapp.main.account.myfavorite.FavoriteActivity
import com.affan.movieapp.main.home.view.HomeFragment
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding

    private lateinit var detailsViewModel: DetailsViewModel

    private lateinit var category : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        detailsViewModel = ViewModelProvider(this, ViewModelFactory.getInstance(this))[DetailsViewModel::class.java]

        binding.ivBack.setOnClickListener {
            finish()
        }

        val id = intent.getIntExtra(HomeFragment.ID, 0)

        category = intent.getStringExtra(HomeFragment.CATEGORY).orEmpty()


        Log.d("cekid", id.toString())
        Log.d("cekcategory", category)

        observeLiveData()
        detailsViewModel.getDetailsMovie(id, category)
        detailsViewModel.getVideos(id, category)

    }


    private fun observeLiveData() {

        detailsViewModel.loading.observe(this) { isLoading ->
            // TODO:
            if (isLoading){
                binding.pbBuffering.visibility = View.VISIBLE
                binding.ivBackdropDetails.visibility  = View.VISIBLE
                binding.ytTrailer.visibility = View.GONE
            } else {
                binding.pbBuffering.visibility = View.GONE
                binding.ivBackdropDetails.visibility  = View.GONE
                binding.ytTrailer.visibility = View.VISIBLE
            }
        }


        //detail response
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
            binding.tvTitleDetail.text = data.title

            val currentDate = data.releaseDate

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

            binding.ivFavorite.setOnClickListener {
                var mCategory = category

                if (mCategory=="movies"){
                    detailsViewModel.setDataMovies(
                        FavoriteMovies(
                            id = data.id,
                            name = null,
                            title = data.title,
                            poster = data.posterPath
                        )
                    )
                    detailsViewModel.getDetailsMovie(data.id!!,"movies")
                } else {
                    detailsViewModel.setDataMovies(
                        FavoriteMovies(
                            id = data.id,
                            name = data.title,
                            title = null,
                            poster = data.posterPath
                        )
                    )
                    detailsViewModel.getDetailsMovie(data.id!!,"series")
                }
                Log.d("Detail Activity",data.title!!)
                Log.d("Snack Detail",category)
                val snackBar = binding.root.let {
                    Snackbar.make(
                        it,
                        "Has Been Added in Your Favorite",
                        Snackbar.LENGTH_LONG)
                }

                snackBar.setAction("OPEN") { snackBar.also {
                    val intent = Intent(this,FavoriteActivity::class.java)
                    intent.putExtra(MCATEGORY,mCategory)
                    startActivity(intent)
                }
                }
                snackBar.setActionTextColor(applicationContext.getColor(R.color.white))
                snackBar.show()
            }
        }
        detailsViewModel.error.observe(this) { error ->
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
            Log.d("asdasd", error)
        }

        //Videos response
        lifecycle.addObserver(binding.ytTrailer)
        detailsViewModel.videoKey.observe(this) { data ->

            Log.d("DetailActivity key", data.toString())

            binding.ytTrailer.addYouTubePlayerListener(object : AbstractYouTubePlayerListener(){
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    super.onReady(youTubePlayer)
                    youTubePlayer.loadVideo(data!!,0F)
                }
            })
        }
    }

    companion object {
        private const val BASE_URL = "https://image.tmdb.org/t/p/w500"
        const val MCATEGORY = "mcategory"
    }
}