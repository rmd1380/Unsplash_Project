package com.example.unsplashproject.fragments.topicfragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.unsplashproject.R
import com.example.unsplashproject.adapter.PhotosAndFeedAdapter
import com.example.unsplashproject.model.response.PhotoResponse
import com.example.unsplashproject.model.response.TopicResponse
import com.example.unsplashproject.services.ServiceApi
import com.example.unsplashproject.services.ServiceBuilder
import retrofit2.Call
import retrofit2.Response

class TopicDetailFragment : Fragment() {
    private lateinit var gridLayoutManager: GridLayoutManager
    private lateinit var ivArrowBack: ImageView
    private lateinit var ivTopicCoverPhoto: ImageView
    private lateinit var tvToolbarTopic: TextView
    private lateinit var tvTopic: TextView
    private lateinit var tvTopicDescription: TextView
    private lateinit var recTopicDetail: RecyclerView
    private lateinit var adapterTopicDetail: PhotosAndFeedAdapter
    var bundle=Bundle()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_topic_detail, container, false)
        init(view)
        return view
    }

    private fun init(view: View) {
        bindView(view)
        setupList()
        ivArrowBack.setOnClickListener {
            findNavController().popBackStack()
        }
        callApi()
        //callApiList()
    }

    private fun callApi() {
        val serviceApi = ServiceBuilder.buildService(ServiceApi::class.java)
        val requestCall = serviceApi.getTopicDetailById(requireArguments().getString("TopicID")!!)
        requestCall.enqueue(object : retrofit2.Callback<TopicResponse> {
            override fun onResponse(
                call: Call<TopicResponse>,
                response: Response<TopicResponse>
            ) {
                if (response.isSuccessful) {
                    Log.d("isSuccessful", response.code().toString())
                    val topic = response.body()!!
                    tvToolbarTopic.text = topic.title.toString()
                    tvTopic.text = topic.title.toString()
                    tvTopicDescription.text = topic.description.toString()

                    Glide
                        .with(context!!)
                        .load(topic.coverPhoto?.urls?.small)
                        .centerCrop()
                        .into(ivTopicCoverPhoto)
                } else {
                    Log.d("isFailed", response.code().toString())
                }
            }

            override fun onFailure(call: Call<TopicResponse>, t: Throwable) {
                Log.d("onFailure", t.message.toString())

            }

        })
    }
//    private fun callApiList() {
//        val serviceApi = ServiceBuilder.buildService(ServiceApi::class.java)
//        val requestCall = serviceApi.getTopicPhotosById(requireArguments().getString("TopicID")!!)
//        requestCall.enqueue(object : retrofit2.Callback<List<PhotoResponse>> {
//            override fun onResponse(
//                call: Call<List<PhotoResponse>>,
//                response: Response<List<PhotoResponse>>
//            ) {
//                if (response.isSuccessful) {
//                    Log.d("isSuccessful", response.code().toString())
//                    val topicList = response.body()!!
//                    adapterTopicDetail.setupList(topicList)
//                } else {
//                    Log.d("isFailed", response.code().toString())
//                }
//            }
//
//            override fun onFailure(call: Call<List<PhotoResponse>>, t: Throwable) {
//                Log.d("onFailure", t.message.toString())
//
//            }
//
//        })
//    }

    private fun setupList() {
        gridLayoutManager = GridLayoutManager(context, 2)
        recTopicDetail.layoutManager = gridLayoutManager
        adapterTopicDetail = PhotosAndFeedAdapter(context) {
            bundle.putString("ImageID",it.id)
            findNavController().navigate(R.id.feedDetailFragment,bundle)
        }
        recTopicDetail.adapter = adapterTopicDetail
    }

    private fun bindView(view: View) {
        ivArrowBack = view.findViewById(R.id.arrow_back_topic)
        recTopicDetail = view.findViewById(R.id.rec_topic_detail)
        ivTopicCoverPhoto = view.findViewById(R.id.topic_iv_cover_photo)
        tvToolbarTopic = view.findViewById(R.id.tv_toolbar_topic)
        tvTopic = view.findViewById(R.id.tv_topic)
        tvTopicDescription = view.findViewById(R.id.topic_description)


    }


}