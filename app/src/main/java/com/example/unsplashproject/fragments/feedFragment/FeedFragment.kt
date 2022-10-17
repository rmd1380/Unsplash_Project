package com.example.unsplashproject.fragments.feedFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.unsplashproject.R
import com.example.unsplashproject.adapter.PhotosAndFeedAdapter
import com.example.unsplashproject.util.snackBar
import com.example.unsplashproject.viewmodels.feedfragmentviewmodels.FeedFragmentViewModel
import com.facebook.shimmer.ShimmerFrameLayout
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class FeedFragment : Fragment() {

    private lateinit var gridLayoutManager: GridLayoutManager
    private lateinit var adapter: PhotosAndFeedAdapter
    private lateinit var recFeed: RecyclerView
    private lateinit var shimmerLayout: ShimmerFrameLayout
    private lateinit var feedLayout:LinearLayout
    private val viewModel: FeedFragmentViewModel by viewModels()
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
        viewModel.getImages().observe(viewLifecycleOwner)
        {

            lifecycleScope.launch(Dispatchers.IO)
            {
                adapter.submitData(it)
            }
        }
        lifecycleScope.launch(viewLifecycleOwner.lifecycleScope.coroutineContext) {
            adapter.loadStateFlow.collectLatest { loadStates ->
                if (loadStates.refresh is LoadState.Loading) {
                    shimmerLayout.startShimmerAnimation()
                    shimmerLayout.visibility=View.VISIBLE
                    recFeed.visibility = View.GONE
                } else {
                    shimmerLayout.stopShimmerAnimation()
                    shimmerLayout.visibility=View.GONE
                    recFeed.visibility = View.VISIBLE
                }

                if (loadStates.append is LoadState.Error) {
                    feedLayout.snackBar("Error", "Check your Internet and try again!")
                }
            }
        }

//        viewModel.getLiveDataObserverPhotoList().observe(viewLifecycleOwner)
//        {
//            when (it) {
//                is Resource.Loading -> {
//
//                }
//                is Resource.Success -> {
//                    adapter.setupList(it.data)
//                    adapter.notifyDataSetChanged()
//                }
//                is Resource.Error -> {
//                    Toast.makeText(context, "Error in getting data", Toast.LENGTH_SHORT).show()
//                }
//            }
//        }
    }

    private fun bindView(view: View) {
        recFeed = view.findViewById(R.id.rec_feed)
        shimmerLayout = view.findViewById(R.id.shimmer_layout)
        feedLayout=view.findViewById(R.id.feed_layout)
    }

    private fun setupList() {

        adapter = PhotosAndFeedAdapter {
            bundle.putString("ImageID", it.id)
            findNavController().navigate(R.id.feedDetailFragment, bundle)
        }
        gridLayoutManager = GridLayoutManager(context, 2)
        recFeed.layoutManager = gridLayoutManager
        recFeed.adapter = adapter

    }

    override fun onResume() {
        super.onResume()
        shimmerLayout.startShimmerAnimation()
    }

    override fun onPause() {
        shimmerLayout.stopShimmerAnimation()
        super.onPause()
    }
}

