package com.example.unsplashproject.fragments.searchfragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.unsplashproject.R
import com.example.unsplashproject.adapter.SearchPhotoAdapter
import com.example.unsplashproject.services.Resource
import com.example.unsplashproject.viewmodels.searchviewmodels.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class PhotoFragment : Fragment() {

    private lateinit var gridLayoutManager: GridLayoutManager
    private lateinit var recPhoto: RecyclerView
    private lateinit var adapter: SearchPhotoAdapter
    private val viewModel: SearchViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_photo, container, false)
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
        viewModel.getLiveDataObserverPhotoSearch(query).observe(viewLifecycleOwner)
        {
            Log.d("ititit","${it.data?.results}")
            when (it) {
                is Resource.Loading -> {

                }
                is Resource.Success -> {
                    adapter.setupList(it.data?.results)
                }
                is Resource.Error -> {
                    Log.d("Error" , "ERROR_SEARCH:: ${it.message}")
                    Toast.makeText(context, "Error in getting data", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun bindView(view: View) {
        recPhoto = view.findViewById(R.id.rec_photo)
    }

    private fun setupList() {
        gridLayoutManager = GridLayoutManager(context, 2)
        recPhoto.layoutManager = gridLayoutManager
        adapter = SearchPhotoAdapter(context) {
        }
        recPhoto.adapter = adapter
    }
}