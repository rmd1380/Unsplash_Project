package com.example.unsplashproject.fragments.feedFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.unsplashproject.R
import com.example.unsplashproject.adapter.PhotosAndFeedAdapter
import com.example.unsplashproject.viewmodels.feedfragmentviewmodels.FeedFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FeedFragment : Fragment() {

    private lateinit var gridLayoutManager: GridLayoutManager
    private lateinit var adapter: PhotosAndFeedAdapter
    private lateinit var recFeed: RecyclerView
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
        val viewModel: FeedFragmentViewModel = ViewModelProvider(this)[FeedFragmentViewModel::class.java]
        viewModel.getLiveDataObserver().observe(viewLifecycleOwner) {
            if (it != null) {
                adapter.setupList(it)
                adapter.notifyDataSetChanged()
            } else {
                Toast.makeText(context, "Error in getting data", Toast.LENGTH_SHORT).show()
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







//    private fun callApi() {
//        val serviceApi= ServiceBuilder.buildService(ServiceApi::class.java)
//        val requestCall=serviceApi.getPhoto()
//        requestCall.enqueue(object :retrofit2.Callback<List<PhotoResponse>>
//        {
//            override fun onResponse(
//                call: Call<List<PhotoResponse>>,
//                response: Response<List<PhotoResponse>>
//            ) {
//                if(response.isSuccessful)
//                {
//                    Log.d("isSuccessful", response.code().toString())
//                    val photoList=response.body()!!
//                    adapter.setupList(photoList)
//                }
//
//                else{
//                    Log.d("isFailed", response.code().toString())
//                }
//            }
//            override fun onFailure(call: Call<List<PhotoResponse>>, t: Throwable) {
//                Log.d("onFailure", t.message.toString())
//
//            }
//
//        })
//    }
