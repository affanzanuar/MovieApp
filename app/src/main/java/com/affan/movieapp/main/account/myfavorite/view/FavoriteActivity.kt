package com.affan.movieapp.main.account.myfavorite.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.affan.movieapp.databinding.ActivityFavoriteBinding
import com.affan.movieapp.model.favorite.FavoriteMovies
import com.affan.movieapp.di.ViewModelFactory
import com.affan.movieapp.main.account.myfavorite.adapter.FavoriteAdapter
import com.affan.movieapp.main.account.myfavorite.viewmodel.FavoriteViewModel
import com.affan.movieapp.main.details.DetailsActivity
import com.affan.movieapp.main.home.view.HomeFragment

class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteBinding
    private lateinit var favoriteAdapter: FavoriteAdapter
    private lateinit var favoriteViewModel: FavoriteViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        favoriteViewModel = ViewModelProvider(
            this,
            ViewModelFactory.getInstance(this)
        )[FavoriteViewModel::class.java]

        setAdapter()
        getObserveLiveData()
        favoriteViewModel.getDataFavorite()

        binding.ivBack.setOnClickListener {
            finish()
        }

        val id = intent.getIntExtra(HomeFragment.ID, 0)
        val category = intent.getStringExtra(HomeFragment.CATEGORY).orEmpty()

        Log.d("cek id", id.toString())
        Log.d("cek category", category)

    }

    private fun getObserveLiveData(){

        favoriteViewModel.isLoading.observe(this) { isLoading ->
            if (isLoading){
                binding.rvFavorite.visibility = View.GONE
            } else {
                binding.rvFavorite.visibility = View.VISIBLE
            }
        }

        favoriteViewModel.cinemaFavorite.observe(this) { data ->
            favoriteAdapter.setDataFavorite(data)
        }
        favoriteViewModel.deleteFavorite.observe(this) {
            favoriteViewModel.getDataFavorite()
        }

        favoriteViewModel.errorMessage.observe(this) {error ->
            Toast.makeText(this,error,Toast.LENGTH_LONG).show()
        }
    }

    private fun setDialog(favoriteMovies: FavoriteMovies){
        val dialogFragment = DeleteDialogFragment {
            favoriteViewModel.deleteDataFavorite(
                favoriteMovies
            )
        }
        dialogFragment.show(supportFragmentManager,null)
    }

    private fun setAdapter(){
        favoriteAdapter = FavoriteAdapter (
            { data: FavoriteMovies -> intentToDetails(data) },
            { data : FavoriteMovies -> setDialog(FavoriteMovies(data.id,data.name,data.title,data.poster))}
                )
        binding.rvFavorite.adapter = favoriteAdapter
        binding.rvFavorite.layoutManager = LinearLayoutManager(this)

    }

    private fun intentToDetails ( item : FavoriteMovies) {

        val intent = Intent(this, DetailsActivity::class.java)
        val parcelable = FavoriteMovies(
            id = item.id,
            name = item.name,
            title = item.title,
            poster = item.poster
        )

        val category = if (item.name?.isNotEmpty() == true){
            "series"
        } else {
            "movies"
        }

        Log.d("FavoriteActivity",item.name.toString())
        Log.d("FavoriteActivity category",category)
        intent.putExtra(HomeFragment.CATEGORY,category)
        intent.putExtra(HomeFragment.ID,item.id)
        intent.putExtra(HomeFragment.EXTRA_DATA_MS,parcelable)
        startActivity(intent)
    }

}