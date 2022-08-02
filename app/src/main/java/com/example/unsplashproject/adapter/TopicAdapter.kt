package com.example.unsplashproject.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.unsplashproject.R
import com.example.unsplashproject.model.response.TopicResponse

class TopicAdapter(private val context: Context?, private var listItem: List<TopicResponse>?=null, private var callback: (TopicResponse) -> Unit) :
    RecyclerView.Adapter<TopicAdapter.TopicItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopicItemViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.topic_item_design, parent, false)
        return TopicItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: TopicItemViewHolder, position: Int) {
        val item= listItem!![position]
        holder.txtTopicItem.text=item.slug
        holder.itemView.setOnClickListener {
            callback.invoke(listItem!![position])
        }

    }

    override fun getItemCount(): Int {
        return listItem?.size ?: 0
    }

    class TopicItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtTopicItem:TextView=itemView.findViewById(R.id.tv_topic_item)

    }
    @SuppressLint("NotifyDataSetChanged")
    fun setupList(list :List<TopicResponse>?)
    {
        this.listItem= list
        notifyDataSetChanged()
    }

}