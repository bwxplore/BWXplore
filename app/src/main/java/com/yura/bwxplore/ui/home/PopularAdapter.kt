package com.yura.bwxplore.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.yura.bwxplore.R
import com.yura.bwxplore.data.Location
import com.yura.bwxplore.databinding.ItemPopularBinding

class PopularAdapter(private val listLocation: ArrayList<Location>) :
    RecyclerView.Adapter<PopularAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback
    private lateinit var binding: ItemPopularBinding

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val view =
            LayoutInflater.from(viewGroup.context).inflate(R.layout.item_popular, viewGroup, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listLocation[position])
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClick(listLocation[holder.adapterPosition])
        }
    }

    override fun getItemCount(): Int = listLocation.size


    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvName: TextView = itemView.findViewById(R.id.tv_popular)
        var imgPhoto: ImageView = itemView.findViewById(R.id.iv_popular)
        fun bind(data: Location) {
            Glide.with(itemView.context)
                .load(data.photo)
                .into(imgPhoto)
            tvName.text = data.name
        }
    }

    interface OnItemClickCallback {
        fun onItemClick(data: Location)
    }
}