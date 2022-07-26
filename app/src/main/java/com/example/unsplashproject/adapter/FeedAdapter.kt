package com.example.unsplashproject.adapter

import android.app.Application
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.unsplashproject.R
import com.example.unsplashproject.fragments.feedFragment.FeedDetailFragment
import com.example.unsplashproject.fragments.feedFragment.FeedFragment
import com.example.unsplashproject.model.FeedModel

class FeedAdapter(private var context: Context?, private var imgList: ArrayList<FeedModel>) :
    RecyclerView.Adapter<FeedAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.feed_item_design, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = imgList[position]
        holder.img.setImageResource(item.img)
        holder.itemView.setOnClickListener { v ->

            val activity = v!!.context as AppCompatActivity
            val feedDetailFragment = FeedDetailFragment()

            activity.supportFragmentManager.beginTransaction()
                .replace(R.id.feed_container, feedDetailFragment).addToBackStack(null)
                .commit()
        }
    }

    override fun getItemCount(): Int {
        return imgList.size
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val img: ImageView = itemView.findViewById(R.id.feed_iv)


    }
}