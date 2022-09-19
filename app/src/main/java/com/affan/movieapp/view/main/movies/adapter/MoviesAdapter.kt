package com.affan.movieapp.view.main.movies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.affan.movieapp.databinding.CardLayoutBinding
import com.affan.movieapp.model.movie.Movie
import com.affan.movieapp.view.main.movies.MoviesData
import com.bumptech.glide.Glide

class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {
    private val movies = mutableListOf<Movie?>()

    inner class MoviesViewHolder(
        private val binding: CardLayoutBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(moviesData: Movie) {
            Glide.with(binding.root)
                .load(moviesData.loadPoster())
                .into(binding.ivPoster)
            binding.tvMovieTitle.text = moviesData.title
            binding.tvDescription.text = moviesData.overview
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        return MoviesViewHolder(
            CardLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        movies[position]?.let { holder.bind(it) }
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    fun setData(data: List<Movie?>) {
        movies.clear()
        movies.addAll(data)
        notifyDataSetChanged()
    }


}