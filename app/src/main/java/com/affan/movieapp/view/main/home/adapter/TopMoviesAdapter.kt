package com.affan.movieapp.view.main.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.affan.movieapp.databinding.ItemTopMoviesBinding
import com.affan.movieapp.model.MoviesOrSeries
import com.bumptech.glide.Glide

class TopMoviesAdapter (
//    private val onClickToDetails : (data : MoviesOrSeries) -> Unit,
    private val viewPager2: ViewPager2
) : RecyclerView.Adapter<TopMoviesAdapter.TopMoviesViewHolder>(){

    private val item = arrayListOf<MoviesOrSeries>()

    inner class TopMoviesViewHolder (val binding: ItemTopMoviesBinding)
        : RecyclerView.ViewHolder(binding.root){
            fun bind (item : MoviesOrSeries){
                Glide.with(binding.root)
                    .load(item.moviesOrSeriesBackDrop)
                    .into(binding.ivItemTopMovies)

                binding.tvTopMoviesTitle.text = item.moviesOrSeriesTitle

                binding.root.setOnClickListener {
//                    onClickToDetails(item)
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
        holder.bind(item[position])
        if (position==this.item.size-1){
            viewPager2.post(runnable)
        }
    }

    override fun getItemCount(): Int {
        return item.size
    }

    @SuppressLint("NotifyDataSetChanged")
    private val runnable = Runnable {
        item.addAll(item)
        notifyDataSetChanged()
    }
    @SuppressLint("NotifyDataSetChanged")
    fun setData (item : List<MoviesOrSeries>){
        this.item.clear()
        this.item.addAll(item)
        notifyDataSetChanged()
    }
}