package com.example.unsplashproject.fragments.feedFragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.unsplashproject.R
import com.example.unsplashproject.model.response.PhotoResponse
import com.example.unsplashproject.services.Resource
import com.example.unsplashproject.viewmodels.feedfragmentviewmodels.FeedDetailFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FeedDetailFragment : Fragment() {

    private lateinit var ivProfile: ImageView
    private lateinit var ivArrowBack: ImageView
    private lateinit var mainPhoto: ImageView
    private lateinit var viewCount: TextView
    private lateinit var downloadCount: TextView
    private lateinit var publishDate: TextView
    private lateinit var camera: TextView
    private lateinit var userName: TextView
    var photoResponse: Resource<PhotoResponse>? = null
    var bundle = Bundle()
    private val viewModel: FeedDetailFragmentViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_feed_detail, container, false)
        init(view)
        return view
    }

    private fun init(view: View) {
        bindView(view)
        viewModel()
        ivProfile.setOnClickListener {
            bundle.putString("ImageUserNameProf", photoResponse?.data?.user?.username)
            findNavController().navigate(R.id.feedProfileFragment, bundle)
        }
        ivArrowBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }


    private fun viewModel() {
        viewModel.getLiveDataObserverPhotoData(requireArguments().getString("ImageID")!!)
            .observe(viewLifecycleOwner)
            {
                when (it) {
                    is Resource.Loading -> {
                    }
                    is Resource.Success -> {
                        photoResponse = it
                        viewCount.text = it.data?.views.toString()
                        downloadCount.text = it.data?.downloads.toString()
                        publishDate.text = it.data?.createdAt.toString()
                        camera.text = it.data?.exif?.name.toString()
                        userName.text = it.data?.user?.name.toString()
                        Glide
                            .with(context!!)
                            .load(it.data?.urls?.regular)
                            .centerCrop()
                            .into(mainPhoto)
                        Glide
                            .with(context!!)
                            .load(it.data?.user?.profileImage?.large)
                            .centerCrop()
                            .into(ivProfile)
                    }
                    is Resource.Error -> {
                        Log.d("getDetail", "Error in getting data " + it.message)
                        Toast.makeText(context, "Error in getting data ", Toast.LENGTH_SHORT).show()
                    }
                }
            }
    }

    private fun bindView(view: View) {
        ivProfile = view.findViewById(R.id.feed_iv_profile_photo)
        ivArrowBack = view.findViewById(R.id.arrow_back)
        mainPhoto = view.findViewById(R.id.feed_iv_main_photo)
        viewCount = view.findViewById(R.id.tv_view_count)
        downloadCount = view.findViewById(R.id.tv_download_count)
        publishDate = view.findViewById(R.id.tv_publish_date)
        camera = view.findViewById(R.id.tv_camera)
        userName = view.findViewById(R.id.tv_profile_username)
    }
}
