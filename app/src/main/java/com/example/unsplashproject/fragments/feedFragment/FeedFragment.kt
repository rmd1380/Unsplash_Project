package com.example.unsplashproject.fragments.feedFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.unsplashproject.R
import com.example.unsplashproject.adapter.PhotosAndFeedAdapter
import com.example.unsplashproject.services.Resource
import com.example.unsplashproject.viewmodels.feedfragmentviewmodels.FeedFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FeedFragment : Fragment() {

    private lateinit var gridLayoutManager: GridLayoutManager
    private lateinit var adapter: PhotosAndFeedAdapter
    private lateinit var recFeed: RecyclerView
    private val viewModel: FeedFragmentViewModel by activityViewModels()
    var bundle = Bundle()
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
        setupList()
        viewModel()
    }

    private fun viewModel() {
        viewModel.getLiveDataObserverPhotoList().observe(viewLifecycleOwner)
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
        recFeed = view.findViewById(R.id.rec_feed)

    }

    private fun setupList() {
        gridLayoutManager = GridLayoutManager(context, 2)
        recFeed.layoutManager = gridLayoutManager
        adapter = PhotosAndFeedAdapter(context) {
            bundle.putString("ImageID", it.id)
            findNavController().navigate(R.id.feedDetailFragment, bundle)
        }
        recFeed.adapter = adapter
    }
}

