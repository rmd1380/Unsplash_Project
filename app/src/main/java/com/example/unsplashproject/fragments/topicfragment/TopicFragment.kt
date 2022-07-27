package com.example.unsplashproject.fragments.topicfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.unsplashproject.R
import com.example.unsplashproject.adapter.FeedAdapter
import com.example.unsplashproject.adapter.TopicAdapter
import com.example.unsplashproject.model.feed.FeedModel
import com.example.unsplashproject.model.topic.TopicModel

class TopicFragment : Fragment() {

    private lateinit var gridLayoutManager: GridLayoutManager
    private lateinit var adapter: TopicAdapter
    private lateinit var recTopic: RecyclerView
    private var item = ArrayList<TopicModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return view(inflater,container)
    }
    private fun view(inflater: LayoutInflater, container: ViewGroup?): View {
        val view: View = inflater.inflate(R.layout.fragment_topic, container, false)
        init(view)
        return view
    }

    private fun init(view: View) {
        bindView(view)
        item.clear()
        item.add(TopicModel("WallPaper"))
        item.add(TopicModel("Current Event"))
        item.add(TopicModel("WallPaper"))
        item.add(TopicModel("Current Event"))
        item.add(TopicModel("3D Renders"))
        item.add(TopicModel("Textures & Patterns ..."))
        item.add(TopicModel("WallPaper"))
        item.add(TopicModel("Current Event"))
        gridLayoutManager = GridLayoutManager(context, 2)
        recTopic.layoutManager = gridLayoutManager
        adapter = TopicAdapter(context!!,item)
        {
            findNavController().navigate(R.id.topicDetailFragment)
        }
        recTopic.adapter = adapter
    }

    private fun bindView(view: View) {
        recTopic = view.findViewById(R.id.rec_topic)

    }
}