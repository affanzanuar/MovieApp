package com.affan.movieapp.main.account.myfavorite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.affan.movieapp.databinding.ActivityFavoriteBinding
import com.affan.movieapp.model.MoviesOrSeries
import com.affan.movieapp.main.account.myfavorite.adapter.FavoriteAdapter
import com.affan.movieapp.main.account.myfavorite.presenter.FavoriteView
import com.affan.movieapp.main.details.DetailsActivity
import com.affan.movieapp.main.home.HomeFragment

class FavoriteActivity : AppCompatActivity(),FavoriteView {

    private lateinit var binding: ActivityFavoriteBinding
    private lateinit var favoriteAdapter: FavoriteAdapter
    private lateinit var favoritePresenterImp: FavoritePresenterImp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setAdapterAndPresenterFavorite()


        binding.ivBack.setOnClickListener {
            finish()
        }
    }

    override fun onResume() {
        super.onResume()
        favoritePresenterImp.getContentListFavorite()
    }

    private fun setAdapterAndPresenterFavorite(){
        favoriteAdapter = FavoriteAdapter { data: MoviesOrSeries -> intentToDetails(data) }
        favoritePresenterImp = FavoritePresenterImp(this)
        binding.rvFavorite.adapter = favoriteAdapter
        binding.rvFavorite.layoutManager = LinearLayoutManager(this)

    }

    private fun intentToDetails ( item : MoviesOrSeries) {
        val intent = Intent(this, DetailsActivity::class.java)
        val parcelable = MoviesOrSeries(
            item.id,
            item.moviesOrSeriesTitle,
            item.moviesOrSeriesPoster,
            item.moviesOrSeriesBackDrop,
            item.moviesOrSeriesGenre,
            item.moviesOrSeriesRating,
            item.moviesOrSeriesIsAdult,
            item.moviesOrSeriesDescription,
            item.releaseDate,
            item.originalLanguage,
            item.voteCount
        )
        intent.putExtra(HomeFragment.EXTRA_DATA_MS,parcelable)
        startActivity(intent)
    }

    override fun onGetDataFavoriteSuccess(data: List<MoviesOrSeries>) {
        favoriteAdapter.setDataFavorite(data)
    }

    override fun onGetDataFavoriteFailure(message: String) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
    }
}