package com.affan.movieapp.view.main.details.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.affan.movieapp.databinding.ItemContainerReviewBinding
import com.affan.movieapp.model.MoviesOrSeries

class ReviewAdapter : RecyclerView.Adapter<ReviewAdapter.ReviewMainHolder>() {

    private val reviewData = mutableListOf<MoviesOrSeries>()

    inner class ReviewMainHolder (private val binding: ItemContainerReviewBinding)
        : RecyclerView.ViewHolder(binding.root) {
            fun bind (position: Int){
                val userReview = reviewData[position]
                binding.tvUserName.text = userReview.moviesOrSeriesTitle
                binding.tvReview.text = userReview.moviesOrSeriesDescription
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewMainHolder {
        return ReviewMainHolder(ItemContainerReviewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)
        )
    }

    override fun onBindViewHolder(holder: ReviewMainHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return reviewData.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setDataReview(data : List<MoviesOrSeries>){
        reviewData.clear()
        reviewData.addAll(data)
        notifyDataSetChanged()
    }
}