package com.example.unsplashproject.fragments.searchFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.unsplashproject.R
import com.example.unsplashproject.adapter.SearchPhotoAdapter
import com.example.unsplashproject.viewmodels.searchviewmodels.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PhotoFragment : Fragment() {

    private lateinit var gridLayoutManager: GridLayoutManager
    private lateinit var recPhoto: RecyclerView
    private lateinit var adapter: SearchPhotoAdapter
    private val viewModel: SearchViewModel by activityViewModels()
    var bundle = Bundle()
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

        viewModel.getPhotoSearchImage(query).observe(viewLifecycleOwner)
        {
            lifecycleScope.launch(Dispatchers.IO) {
                adapter.submitData(it)
            }
        }
    }

    private fun bindView(view: View) {
        recPhoto = view.findViewById(R.id.rec_photo)
    }

    private fun setupList() {
        gridLayoutManager = GridLayoutManager(context, 2)
        recPhoto.layoutManager = gridLayoutManager
        adapter = SearchPhotoAdapter {
            bundle.putString("ImageID", it.id)
            findNavController().navigate(R.id.feedDetailFragment,bundle)
        }
        recPhoto.adapter = adapter
    }
}