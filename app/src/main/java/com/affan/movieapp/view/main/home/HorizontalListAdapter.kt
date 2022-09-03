package com.affan.movieapp.view.main.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.affan.movieapp.databinding.ItemHorizontalContainerBinding
import com.affan.movieapp.model.MoviesOrSeries
import com.bumptech.glide.Glide

class HorizontalListAdapter(
    private val onClickToDetails : (data : MoviesOrSeries) -> Unit
) : RecyclerView.Adapter<HorizontalListAdapter.HorizontalGridViewHolder>() {

    private val item = arrayListOf<MoviesOrSeries>()

    inner class HorizontalGridViewHolder (val binding: ItemHorizontalContainerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item : MoviesOrSeries){
            Glide.with(binding.root)
                .load(item.moviesOrSeriesImage)
                .into(binding.ivMoviesOrSeries)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorizontalGridViewHolder {
        return HorizontalGridViewHolder(ItemHorizontalContainerBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)
        )
    }

    override fun onBindViewHolder(holder: HorizontalGridViewHolder, position: Int) {
        holder.bind(item[position])

        holder.binding.root.setOnClickListener {
            onClickToDetails(item[position])
        }
    }

    override fun getItemCount(): Int {
        return item.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData (item : List<MoviesOrSeries>){
        this.item.clear()
        this.item.addAll(item)
        notifyDataSetChanged()
    }
}