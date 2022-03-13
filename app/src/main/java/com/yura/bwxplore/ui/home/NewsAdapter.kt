package com.yura.bwxplore.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yura.bwxplore.data.remote.entities.ArticlesItem
import com.yura.bwxplore.databinding.ItemNewsBinding

class NewsAdapter(private val listNews: ArrayList<ArticlesItem>) :
    RecyclerView.Adapter<NewsAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnNewsClickCallback
    private lateinit var binding: ItemNewsBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listNews[position])
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClick(listNews[holder.adapterPosition])
        }
    }

    override fun getItemCount(): Int = listNews.size

    class ListViewHolder(itemView: ItemNewsBinding) : RecyclerView.ViewHolder(itemView.root) {
        var tvName: TextView = itemView.tvTitleNews
        private var imgPhoto: ImageView = itemView.imgNews
        fun bind(data: ArticlesItem) {
            Glide.with(itemView.context)
                .load(data.urlToImage)
                .into(imgPhoto)
            tvName.text = data.title
        }
    }

    interface OnNewsClickCallback {
        fun onItemClick(data: ArticlesItem)
    }
}