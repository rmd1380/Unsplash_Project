package com.example.unsplashproject.fragments.topicFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.unsplashproject.R
import com.example.unsplashproject.adapter.TopicAdapter
import com.example.unsplashproject.services.Resource
import com.example.unsplashproject.viewmodels.topicfragmentviewmodels.TopicFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TopicFragment : Fragment() {

    private lateinit var gridLayoutManager: GridLayoutManager
    private lateinit var adapter: TopicAdapter
    private lateinit var recTopic: RecyclerView
    private val viewModel: TopicFragmentViewModel by activityViewModels()
    var bundle = Bundle()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return view(inflater, container)
    }

    private fun view(inflater: LayoutInflater, container: ViewGroup?): View {
        val view: View = inflater.inflate(R.layout.fragment_topic, container, false)
        init(view)
        return view
    }

    private fun init(view: View) {
        bindView(view)
        setupList()
        viewModel()
    }

    private fun viewModel() {
        viewModel.getLiveDataObserverTopicList().observe(viewLifecycleOwner)
        {
            when (it) {
                is Resource.Loading -> {

                }
                is Resource.Success -> {
                    adapter.setupList(it.data)
                    adapter.notifyDataSetChanged()
                }
                is Resource.Error -> {
                    Toast.makeText(context, "Error in getting data", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun bindView(view: View) {
        recTopic = view.findViewById(R.id.rec_topic)

    }


    private fun setupList() {
        gridLayoutManager = GridLayoutManager(context, 2)
        recTopic.layoutManager = gridLayoutManager
        adapter = TopicAdapter(context!!)
        {
            bundle.putString("TopicID", it.id)
            findNavController().navigate(R.id.topicDetailFragment, bundle)
        }
        recTopic.adapter = adapter

    }
}