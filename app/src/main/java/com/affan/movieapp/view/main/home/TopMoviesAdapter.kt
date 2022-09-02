package com.affan.movieapp.view.main.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.affan.movieapp.R
import com.affan.movieapp.databinding.ItemTopMoviesBinding
import com.affan.movieapp.model.TopMovies
import com.bumptech.glide.Glide

class TopMoviesAdapter (
    private val item : ArrayList<TopMovies>,
    private val onClickToDetails : (data : TopMovies) -> Unit,
    private val viewPager2: ViewPager2
) : RecyclerView.Adapter<TopMoviesAdapter.TopMoviesViewHolder>(){

    class TopMoviesViewHolder (val binding: ItemTopMoviesBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopMoviesViewHolder {
        return TopMoviesViewHolder(
            ItemTopMoviesBinding.inflate(LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TopMoviesViewHolder, position: Int) {
        val item = item[position]

        Glide.with(holder.binding.root)
            .load(item.topMoviesImage)
            .into(holder.binding.ivItemTopMovies)

        holder.binding.apply {
            tvTopMoviesTitle.text = item.topMoviesTitle

            root.setOnClickListener {
                onClickToDetails(item)
            }
        }
        if (position==this.item.size-1){
            viewPager2.post(runnable)
        }
    }

    override fun getItemCount(): Int {
        return item.size
    }

    private val runnable = Runnable {
        item.addAll(item)
        notifyDataSetChanged()
    }
}