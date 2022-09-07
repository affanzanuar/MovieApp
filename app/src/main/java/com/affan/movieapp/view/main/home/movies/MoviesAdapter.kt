package com.affan.movieapp.view.main.home.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.affan.movieapp.databinding.CardLayoutBinding
import com.affan.movieapp.databinding.FragmentMoviesBinding

class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {
    private val movies = arrayListOf<MoviesData>()

    inner class MoviesViewHolder(
        private val binding: CardLayoutBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(moviesData: MoviesData) {
            binding.tvMovieTitle.text = moviesData.moviesTitle
            binding.tvDescription.text = moviesData.moviesDescription
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        return MoviesViewHolder(
            CardLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    fun setData(data: List<MoviesData>) {
        movies.clear()
        movies.addAll(data)
        notifyDataSetChanged()
    }


}