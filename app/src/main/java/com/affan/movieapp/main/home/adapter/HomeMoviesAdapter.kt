package com.affan.movieapp.main.home.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.affan.movieapp.R
import com.affan.movieapp.databinding.ItemHorizontalContainerBinding
import com.affan.movieapp.data.model.movie.Movie
import com.bumptech.glide.Glide

class HomeMoviesAdapter(
    private val onClickToDetails : (data : Movie) -> Unit
) : RecyclerView.Adapter<HomeMoviesAdapter.MovieListViewHolder>() {

    private val itemMovie = mutableListOf<Movie?>()

    inner class MovieListViewHolder (val binding: ItemHorizontalContainerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item : Movie){
            Glide.with(binding.root)
                .load(item.loadPoster())
                .placeholder(R.drawable.ic_default_poster)
                .into(binding.ivMoviesOrSeries)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolder {
        return MovieListViewHolder(ItemHorizontalContainerBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)
        )
    }

    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {
        itemMovie[position]?.let { holder.bind(it) }

        holder.binding.root.setOnClickListener {
            itemMovie[position]?.let { it1 -> onClickToDetails(it1) }
        }
    }

    override fun getItemCount(): Int {
        return itemMovie.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setDataMovies (item : List<Movie?>){
        Log.d("Main Adapter setData", item.toString())
        itemMovie.clear()
        itemMovie.addAll(item)
        notifyDataSetChanged()
    }
}