package com.example.unsplashproject.fragments.topicfragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.unsplashproject.R
import com.example.unsplashproject.adapter.TopicAdapter
import com.example.unsplashproject.model.response.PhotoResponse
import com.example.unsplashproject.model.response.TopicResponse
import com.example.unsplashproject.model.topic.TopicModel
import com.example.unsplashproject.services.Service
import com.example.unsplashproject.services.ServiceBuilder
import retrofit2.Call
import retrofit2.Response

class TopicFragment : Fragment() {

    private lateinit var gridLayoutManager: GridLayoutManager
    private lateinit var adapter: TopicAdapter
    private lateinit var recTopic: RecyclerView
    var bundle=Bundle()
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
        setupList()
        callApi()
    }

    private fun bindView(view: View) {
        recTopic = view.findViewById(R.id.rec_topic)

    }
    private fun callApi() {
        val service= ServiceBuilder.buildService(Service::class.java)
        val requestCall=service.getTopic()
        requestCall.enqueue(object :retrofit2.Callback<List<TopicResponse>>
        {
            override fun onResponse(
                call: Call<List<TopicResponse>>,
                response: Response<List<TopicResponse>>
            ) {
                if(response.isSuccessful)
                {
                    Log.d("isSuccessful", response.code().toString())
                    val topicList = response.body()!!
                    adapter.setupList(topicList)
                }

                else{
                    Log.d("isFailed", response.code().toString())
                }
            }
            override fun onFailure(call: Call<List<TopicResponse>>, t: Throwable) {
                Log.d("onFailure", t.message.toString())

            }

        })
    }

    private fun setupList()
    {
        gridLayoutManager = GridLayoutManager(context, 2)
        recTopic.layoutManager = gridLayoutManager
        adapter = TopicAdapter(context!!)
        {
            bundle.putString("TopicID",it.id)
            findNavController().navigate(R.id.topicDetailFragment,bundle)
        }
        recTopic.adapter = adapter

    }
}