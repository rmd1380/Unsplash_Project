package com.example.unsplashproject.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.unsplashproject.R
import com.example.unsplashproject.model.feed.FeedModel
import com.example.unsplashproject.model.response.PhotoResponse
import com.example.unsplashproject.model.response.TopicResponse
import com.example.unsplashproject.model.response.TopicResponseForPhotos
import com.example.unsplashproject.model.sitetopicmodel.PreviewPhotos
import com.example.unsplashproject.model.topic.TopicModel

class TopicAdapterForPhotos(private val context: Context?, private var listItem: List<TopicResponseForPhotos>?=null, private var callback: (TopicResponseForPhotos) -> Unit) :
    RecyclerView.Adapter<TopicAdapterForPhotos.TopicItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopicItemViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.feed_item_design, parent, false)
        return TopicItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: TopicItemViewHolder, position: Int) {
        val item= listItem!![position]

        Glide
            .with(context!!)
            .load(item.urls?.small)
            .centerCrop()
            .into(holder.ivTopicItem)
        holder.itemView.setOnClickListener {
            callback.invoke(listItem!![position])
        }

    }

    override fun getItemCount(): Int {
        return listItem?.size ?: 0
    }

    class TopicItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivTopicItem:ImageView=itemView.findViewById(R.id.feed_iv)

    }
    fun setupList(list: List<TopicResponseForPhotos>)
    {
        this.listItem= list
        notifyDataSetChanged()
    }

}