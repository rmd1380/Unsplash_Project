package com.example.unsplashproject.fragments.feedFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.unsplashproject.R
import com.example.unsplashproject.adapter.FeedAdapter
import com.example.unsplashproject.model.feed.FeedModel

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
        val view: View = inflater.inflate(R.layout.fragment_feed, container, false)
        init(view)
        return view
    }
    private fun init(view: View) {
        bindView(view)
        item.clear()
        item.add(FeedModel(R.drawable.ic_launcher_background))
        item.add(FeedModel(R.drawable.ic_launcher_background))
        item.add(FeedModel(R.drawable.ic_launcher_background))
        item.add(FeedModel(R.drawable.ic_launcher_background))
        item.add(FeedModel(R.drawable.ic_launcher_background))
        item.add(FeedModel(R.drawable.ic_launcher_background))
        item.add(FeedModel(R.drawable.ic_launcher_background))
        item.add(FeedModel(R.drawable.ic_launcher_background))
        item.add(FeedModel(R.drawable.ic_launcher_background))
        item.add(FeedModel(R.drawable.ic_launcher_background))
        item.add(FeedModel(R.drawable.ic_launcher_background))
        item.add(FeedModel(R.drawable.ic_launcher_background))
        item.add(FeedModel(R.drawable.ic_launcher_background))
        item.add(FeedModel(R.drawable.ic_launcher_background))
        item.add(FeedModel(R.drawable.ic_launcher_background))
        item.add(FeedModel(R.drawable.ic_launcher_background))
        item.add(FeedModel(R.drawable.ic_launcher_background))
        item.add(FeedModel(R.drawable.ic_launcher_background))
        item.add(FeedModel(R.drawable.ic_launcher_background))
        item.add(FeedModel(R.drawable.ic_launcher_background))
        item.add(FeedModel(R.drawable.ic_launcher_background))
        item.add(FeedModel(R.drawable.ic_launcher_background))
        item.add(FeedModel(R.drawable.ic_launcher_background))
        item.add(FeedModel(R.drawable.ic_launcher_background))
        item.add(FeedModel(R.drawable.ic_launcher_background))
        item.add(FeedModel(R.drawable.ic_launcher_background))
        item.add(FeedModel(R.drawable.ic_launcher_background))
        gridLayoutManager = GridLayoutManager(context, 2)
        recFeed.layoutManager = gridLayoutManager
        adapter = FeedAdapter(context, item) {
            findNavController().navigate(R.id.feedDetailFragment)
        }
        recFeed.adapter = adapter
    }

    private fun bindView(view: View) {
        recFeed = view.findViewById(R.id.rec_feed)

    }
}
