package com.example.unsplashproject.fragments.feedFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.unsplashproject.R
import com.example.unsplashproject.adapter.FeedAdapter
import com.example.unsplashproject.model.FeedModel

class FeedFragment : Fragment() {

    private lateinit var gridLayoutManager: GridLayoutManager
    private lateinit var adapter: FeedAdapter
    private lateinit var recFeed: RecyclerView
    private var item = ArrayList<FeedModel>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        item.add(FeedModel(R.drawable.ic_launcher_background))
        item.add(FeedModel(R.drawable.ic_launcher_background))
        item.add(FeedModel(R.drawable.ic_launcher_background))
        item.add(FeedModel(R.drawable.ic_launcher_background))
        item.add(FeedModel(R.drawable.ic_launcher_background))
        item.add(FeedModel(R.drawable.ic_launcher_background))
        item.add(FeedModel(R.drawable.ic_launcher_background))
        item.add(FeedModel(R.drawable.ic_launcher_background))
        item.add(FeedModel(R.drawable.ic_launcher_background))

        return view(inflater, container)
    }

    private fun view(inflater: LayoutInflater, container: ViewGroup?): View {
        val view: View = inflater.inflate(R.layout.fragment_feed, container, false)
        recFeed = view.findViewById(R.id.rec_feed)
        gridLayoutManager = GridLayoutManager(context, 2)
        recFeed.layoutManager = gridLayoutManager
        adapter = FeedAdapter(context, item)
        recFeed.adapter = adapter

        return view
    }
}
