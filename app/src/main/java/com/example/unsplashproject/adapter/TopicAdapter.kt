package com.example.unsplashproject.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.unsplashproject.R
import com.example.unsplashproject.model.topic.TopicModel

class TopicAdapter(private val context: Context, private val listItem: ArrayList<TopicModel>) :
    RecyclerView.Adapter<TopicAdapter.TopicItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopicItemViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.topic_item_design, parent, false)
        return TopicItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: TopicItemViewHolder, position: Int) {
        val item=listItem[position]
        holder.txtTopicItem.text=item.txtItemTopic

    }

    override fun getItemCount(): Int {
        return listItem.size
    }

    class TopicItemViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val txtTopicItem:TextView=v.findViewById(R.id.tv_topic_item)

    }

}