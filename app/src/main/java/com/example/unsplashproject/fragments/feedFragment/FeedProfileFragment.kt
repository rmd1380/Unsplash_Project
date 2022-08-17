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
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.unsplashproject.R
import com.example.unsplashproject.adapter.PhotosAndFeedAdapter
import com.example.unsplashproject.services.Resource
import com.example.unsplashproject.viewmodels.feedfragmentviewmodels.FeedProfileFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FeedProfileFragment : Fragment() {
    private lateinit var gridLayoutManager: GridLayoutManager
    private lateinit var ivArrowBack: ImageView
    private lateinit var ivProfile: ImageView
    private lateinit var recProfile: RecyclerView
    private lateinit var adapterProfile: PhotosAndFeedAdapter
    private lateinit var profileUserName: TextView
    private lateinit var toolbarUsername: TextView
    private lateinit var profileUserBio: TextView
    var bundle = Bundle()
    private val viewModel: FeedProfileFragmentViewModel by activityViewModels()

    //private var photoList=ArrayList<PhotoResponse>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_feed_profile, container, false)
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
        viewModel.getLiveDataObserverUserByUsername(requireArguments().getString("ImageUserNameProf")!!)
            .observe(viewLifecycleOwner)
            {
                when (it) {
                    is Resource.Loading -> {

                    }
                    is Resource.Success -> {
                        profileUserName.text = it.data?.username
                        toolbarUsername.text = it.data?.username
                        profileUserBio.text = it.data?.bio
                        Glide
                            .with(context!!)
                            .load(it.data?.profileImage?.large)
                            .centerCrop()
                            .into(ivProfile)
                    }
                    is Resource.Error -> {
                        Log.d("getProfileDetail", "Error in getting data " + it.message)
                        Toast.makeText(context, "Error in getting data", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        viewModel.getLiveDataObserverUserPhotoList(requireArguments().getString("ImageUserNameProf")!!)
            .observe(viewLifecycleOwner)
            {
                when (it) {
                    is Resource.Loading -> {

                    }
                    is Resource.Success -> {
                        adapterProfile.setupList(it.data)
                        adapterProfile.notifyDataSetChanged()
                    }
                    is Resource.Error -> {
                        Toast.makeText(context, "Error in getting data", Toast.LENGTH_SHORT).show()
                    }
                }
            }
    }

    private fun setupList() {
        gridLayoutManager = GridLayoutManager(context, 2)
        recProfile.layoutManager = gridLayoutManager
        adapterProfile = PhotosAndFeedAdapter(context) {
            bundle.putString("ImageID", it.id)
            findNavController().navigate(R.id.feedDetailFragment, bundle)
        }
        recProfile.adapter = adapterProfile

    }

    private fun bindView(view: View) {
        ivArrowBack = view.findViewById(R.id.arrow_back_profile)
        recProfile = view.findViewById(R.id.rec_profile)
        ivProfile = view.findViewById(R.id.iv_profile)
        profileUserName = view.findViewById(R.id.profile_username)
        toolbarUsername = view.findViewById(R.id.toolbar_username)
        profileUserBio = view.findViewById(R.id.profile_bio)
    }
}

