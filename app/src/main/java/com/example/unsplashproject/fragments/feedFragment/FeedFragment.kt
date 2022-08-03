package com.example.unsplashproject.fragments.feedFragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.unsplashproject.R
import com.example.unsplashproject.adapter.PhotosAndFeedAdapter
import com.example.unsplashproject.model.response.PhotoResponse
import com.example.unsplashproject.services.Service
import com.example.unsplashproject.services.ServiceBuilder
import retrofit2.Call
import retrofit2.Response

class FeedFragment : Fragment() {

    private lateinit var gridLayoutManager: GridLayoutManager
    private lateinit var adapter: PhotosAndFeedAdapter
    private lateinit var recFeed: RecyclerView
    var bundle=Bundle()
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
        callApi()
    }

    private fun callApi() {
        val service= ServiceBuilder.buildService(Service::class.java)
        val requestCall=service.getPhoto()
        requestCall.enqueue(object :retrofit2.Callback<List<PhotoResponse>>
        {
            override fun onResponse(
                call: Call<List<PhotoResponse>>,
                response: Response<List<PhotoResponse>>
            ) {
                if(response.isSuccessful)
                {
                    Log.d("isSuccessful", response.code().toString())
                    val photoList=response.body()!!
                    adapter.setupList(photoList)
                }

                else{
                    Log.d("isFailed", response.code().toString())
                }
            }
            override fun onFailure(call: Call<List<PhotoResponse>>, t: Throwable) {
                Log.d("onFailure", t.message.toString())

            }

        })
    }

    private fun bindView(view: View) {
        recFeed = view.findViewById(R.id.rec_feed)

    }

    private fun setupList() {
        gridLayoutManager = GridLayoutManager(context, 2)
        recFeed.layoutManager = gridLayoutManager
        adapter = PhotosAndFeedAdapter(context) {
            bundle.putString("ImageID", it.id)
            findNavController().navigate(R.id.feedDetailFragment,bundle)
        }
        recFeed.adapter = adapter
    }
}
