package com.example.unsplashproject.fragments.topicfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.unsplashproject.R
import com.example.unsplashproject.adapter.PhotosAndFeedAdapter
import com.example.unsplashproject.services.Resource
import com.example.unsplashproject.viewmodels.topicfragmentviewmodels.TopicDetailFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TopicDetailFragment : Fragment() {
    private lateinit var gridLayoutManager: GridLayoutManager
    private lateinit var ivArrowBack: ImageView
    private lateinit var ivTopicCoverPhoto: ImageView
    private lateinit var tvToolbarTopic: TextView
    private lateinit var tvTopic: TextView
    private lateinit var tvTopicDescription: TextView
    private lateinit var recTopicDetail: RecyclerView
    private lateinit var adapterTopicDetail: PhotosAndFeedAdapter
    private val viewModel: TopicDetailFragmentViewModel by activityViewModels()
    var bundle = Bundle()

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
        viewModel()
        ivArrowBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }


    private fun viewModel() {
        viewModel.getLiveDataObserverTopicDetail(requireArguments().getString("TopicID")!!)
            .observe(viewLifecycleOwner)
            {
                when (it) {
                    is Resource.Loading -> {
                    }
                    is Resource.Success -> {
                        tvToolbarTopic.text = it.data?.title.toString()
                        tvTopic.text = it.data?.title.toString()
                        tvTopicDescription.text = it.data?.description.toString()

                        Glide
                            .with(context!!)
                            .load(it.data?.coverPhoto?.urls?.small)
                            .centerCrop()
                            .into(ivTopicCoverPhoto)
                    }
                    is Resource.Error -> {
                        Toast.makeText(context, "Error in getting data", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        viewModel.getLiveDataObserverTopicPhoto(requireArguments().getString("TopicID")!!)
            .observe(viewLifecycleOwner)
            {
                when (it) {
                    is Resource.Loading -> {
                    }
                    is Resource.Success -> {
                        adapterTopicDetail.setupList(it.data)
                        adapterTopicDetail.notifyDataSetChanged()
                    }
                    is Resource.Error -> {
                        Toast.makeText(context, "Error in getting data", Toast.LENGTH_SHORT).show()
                    }
                }
            }
    }

    private fun setupList() {
        gridLayoutManager = GridLayoutManager(context, 2)
        recTopicDetail.layoutManager = gridLayoutManager
        adapterTopicDetail = PhotosAndFeedAdapter(context) {
            bundle.putString("ImageID", it.id)
            findNavController().navigate(R.id.feedDetailFragment, bundle)
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
