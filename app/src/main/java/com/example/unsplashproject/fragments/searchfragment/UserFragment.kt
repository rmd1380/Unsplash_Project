package com.example.unsplashproject.fragments.searchfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.unsplashproject.R
import com.example.unsplashproject.adapter.SearchUserAdapter
import com.example.unsplashproject.services.Resource
import com.example.unsplashproject.viewmodels.searchviewmodels.SearchViewModel

class UserFragment : Fragment() {

    private lateinit var gridLayoutManager: GridLayoutManager
    private lateinit var recUser: RecyclerView
    lateinit var adapter: SearchUserAdapter
    private val viewModel: SearchViewModel by activityViewModels()

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
        viewModel.getLiveDataObserverUserSearch(query).observe(viewLifecycleOwner)
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
        recUser = view.findViewById(R.id.rec_user)
    }
    private fun setupList() {
        gridLayoutManager = GridLayoutManager(context, 2)
        recUser.layoutManager = gridLayoutManager
        adapter = SearchUserAdapter(context) {
        }
        recUser.adapter = adapter
    }

}
