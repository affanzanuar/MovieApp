package com.affan.movieapp.view.main.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.affan.movieapp.R
import com.affan.movieapp.databinding.ItemTopMoviesBinding
import com.affan.movieapp.model.trending.Trending
import com.bumptech.glide.Glide

class TrendingAdapter (
    private val onClickToDetails : (data : Trending) -> Unit,
    private val viewPager2: ViewPager2
) : RecyclerView.Adapter<TrendingAdapter.TopMoviesViewHolder>(){

    private val itemMoviesOrSeries = mutableListOf<Trending?>()

    inner class TopMoviesViewHolder (val binding: ItemTopMoviesBinding)
        : RecyclerView.ViewHolder(binding.root){
            fun bind (item : Trending){
                Glide.with(binding.root)
                    .load(item.loadBackdrop())
                    .placeholder(R.drawable.ic_default_top_movies)
                    .into(binding.ivItemTopMovies)

                binding.tvTopMoviesTitle.text = item.title ?: item.name

//                if (item.title?.isNotEmpty() == true){
//                    binding.tvTopMoviesTitle.text = item.title
//                } else {
//                    binding.tvTopMoviesTitle.text = item.name
//                }


                binding.root.setOnClickListener {
                    onClickToDetails(item)
                }
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopMoviesViewHolder {
        return TopMoviesViewHolder(
            ItemTopMoviesBinding.inflate(LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TopMoviesViewHolder, position: Int) {
        itemMoviesOrSeries[position]?.let { holder.bind(it) }
        if (position==this.itemMoviesOrSeries.size-1){
            viewPager2.post(runnable)
        }
    }

    override fun getItemCount(): Int {
        return itemMoviesOrSeries.size
    }

    @SuppressLint("NotifyDataSetChanged")
    private val runnable = Runnable {
        itemMoviesOrSeries.addAll(itemMoviesOrSeries)
        notifyDataSetChanged()
    }
    @SuppressLint("NotifyDataSetChanged")
    fun setData (item : List<Trending?>){
        itemMoviesOrSeries.clear()
        itemMoviesOrSeries.addAll(item)
        notifyDataSetChanged()
    }
}