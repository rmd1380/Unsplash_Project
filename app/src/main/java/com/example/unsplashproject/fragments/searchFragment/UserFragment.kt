package com.example.unsplashproject.fragments.searchFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.unsplashproject.R
import com.example.unsplashproject.adapter.SearchUserAdapter
import com.example.unsplashproject.model.sitesearchusermodel.Results
import com.example.unsplashproject.util.snackBar
import com.example.unsplashproject.viewmodels.searchviewmodels.SearchViewModel
import com.facebook.shimmer.ShimmerFrameLayout
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class UserFragment : Fragment() {

    private lateinit var gridLayoutManager: GridLayoutManager
    private lateinit var recUser: RecyclerView
    lateinit var adapter: SearchUserAdapter
    private lateinit var shimmerLayout: ShimmerFrameLayout
    private lateinit var searchUserLayout: FrameLayout
    private val viewModel: SearchViewModel by activityViewModels()
    var bundle = Bundle()
    var response: Results? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_user, container, false)
        init(view)
        return view
    }

    private fun init(view: View) {
        bindView(view)
        setupList()
        viewModel.mQuery.observe(viewLifecycleOwner) {
            viewModel(it)
        }
    }

    private fun viewModel(query: String) {
        viewModel.getUserSearchImage(query).observe(viewLifecycleOwner)
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
                    shimmerLayout.visibility = View.VISIBLE
                    recUser.visibility = View.GONE
                } else {
                    shimmerLayout.stopShimmerAnimation()
                    shimmerLayout.visibility = View.GONE
                    recUser.visibility = View.VISIBLE
                }

                if (loadStates.append is LoadState.Error) {
                    searchUserLayout.snackBar("Error", "Check your Internet and try again!")
                }
            }
        }
    }

    private fun bindView(view: View) {
        recUser = view.findViewById(R.id.rec_user)
        shimmerLayout = view.findViewById(R.id.shimmer_layout_user_search)
        searchUserLayout = view.findViewById(R.id.search_layout_user)
    }

    private fun setupList() {
        gridLayoutManager = GridLayoutManager(context, 3)
        recUser.layoutManager = gridLayoutManager
        adapter = SearchUserAdapter {
            response = it
            bundle.putString("ImageUserNameProf", it.username)
            findNavController().navigate(R.id.feedProfileFragment, bundle)
        }
        recUser.adapter = adapter
    }

}
