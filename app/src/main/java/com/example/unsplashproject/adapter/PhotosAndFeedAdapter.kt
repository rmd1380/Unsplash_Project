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
import com.example.unsplashproject.model.response.FeedPhotoResponse

class PhotosAndFeedAdapter(
    private var callback: ((FeedPhotoResponse) -> Unit)?
) :
    PagingDataAdapter<FeedPhotoResponse, PhotosAndFeedAdapter.ViewHolder>(FeedDiffUtil) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.photo_item_design, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        Glide
            .with(holder.img)
            .load(item?.urls?.regular)
            .centerCrop()
            .into(holder.img)
        holder.itemView.setOnClickListener {
            callback!!.invoke(getItem(position)!!)
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val img: ImageView = itemView.findViewById(R.id.photo_iv)
    }


    object FeedDiffUtil : DiffUtil.ItemCallback<FeedPhotoResponse>() {
        override fun areItemsTheSame(oldItem: FeedPhotoResponse, newItem: FeedPhotoResponse): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: FeedPhotoResponse, newItem: FeedPhotoResponse): Boolean {
            return areItemsTheSame(oldItem, newItem)
        }
    }
}
