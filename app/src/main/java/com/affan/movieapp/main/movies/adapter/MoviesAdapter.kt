package com.affan.movieapp.main.movies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.affan.movieapp.databinding.CardLayoutBinding
import com.affan.movieapp.model.movie.Movie
import com.bumptech.glide.Glide

class MoviesAdapter(
    private val onClickToDetails : (data : Movie) -> Unit
) : RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {
    private val movies = mutableListOf<Movie?>()

    inner class MoviesViewHolder(
        val binding: CardLayoutBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(moviesData: Movie) {
            Glide.with(binding.root)
                .load(moviesData.loadPoster())
                .into(binding.ivPoster)
            binding.tvMovieTitle.text = moviesData.title
            binding.tvDescription.text = moviesData.voteAverage.toString() + "/10"
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

        holder.binding.root.setOnClickListener {
            movies[position]?.let { it1 -> onClickToDetails(it1) }
        }
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