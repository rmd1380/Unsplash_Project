package com.example.unsplashproject.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.unsplashproject.R
import com.example.unsplashproject.model.sitesearchphotomodel.Results

class SearchPhotoAdapter(
    private var callback: (Results) -> Unit
) :
    PagingDataAdapter<Results, SearchPhotoAdapter.SearchPhotoViewHolder>(SearchPhotoDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchPhotoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.photo_item_design, parent, false)
        return SearchPhotoViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchPhotoViewHolder, position: Int) {
        val item = getItem(position)
        Glide
            .with(holder.img)
            .load(item?.urls?.regular)
            .centerCrop()
            .into(holder.img)
        holder.itemView.setOnClickListener {
            callback.invoke(getItem(position)!!)
        }

    }

    class SearchPhotoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val img: ImageView = itemView.findViewById(R.id.photo_iv)

    }

    object SearchPhotoDiffUtil : DiffUtil.ItemCallback<Results>() {
        override fun areItemsTheSame(oldItem: Results, newItem: Results): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Results, newItem: Results): Boolean {
            return areItemsTheSame(oldItem, newItem)
        }
    }

}