package com.example.unsplashproject.fragments.feedFragment

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
import com.example.unsplashproject.services.Service
import com.example.unsplashproject.services.ServiceBuilder
import retrofit2.Call
import retrofit2.Response

class FeedProfileFragment : Fragment() {
    private lateinit var gridLayoutManager: GridLayoutManager
    private lateinit var ivArrowBack:ImageView
    private lateinit var ivProfile:ImageView
    private lateinit var recProfile:RecyclerView
    private lateinit var adapterProfile:PhotosAndFeedAdapter
    private lateinit var profileUserName:TextView
    private lateinit var toolbarUsername:TextView
    private lateinit var profileUserBio:TextView
    var bundle=Bundle()
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
        callApi()
        callApi2()
        ivArrowBack.setOnClickListener{
            findNavController().popBackStack()
        }

    }
    private fun callApi() {
        val service= ServiceBuilder.buildService(Service::class.java)
        val requestCall=service.getPhotoDetailById(requireArguments().getString("ImageIDProf")!!)
        requestCall.enqueue(object :retrofit2.Callback<PhotoResponse>
        {
            override fun onResponse(
                call: Call<PhotoResponse>,
                response: Response<PhotoResponse>
            ) {
                if(response.isSuccessful)
                {
                    Log.d("isSuccessful", response.code().toString())
                    val photo = response.body()!!
                    profileUserName.text=photo.user?.username
                    toolbarUsername.text=photo.user?.username
                    profileUserBio.text=photo.user?.bio

                    Glide
                        .with(context!!)
                        .load(photo.user?.profileImage?.small)
                        .centerCrop()
                        .into(ivProfile)

                }

                else{
                    Log.d("isFailed", response.code().toString())
                }
            }
            override fun onFailure(call: Call<PhotoResponse>, t: Throwable) {
                Log.d("onFailure", t.message.toString())

            }

        })
    }
    private fun callApi2() {
        val service= ServiceBuilder.buildService(Service::class.java)
        val requestCall=service.getUserByUsername(requireArguments().getString("ImageUserNameProf")!!)
        requestCall.enqueue(object :retrofit2.Callback<List<PhotoResponse>>
        {
            override fun onResponse(
                call: Call<List<PhotoResponse>>,
                response: Response<List<PhotoResponse>>
            ) {
                if(response.isSuccessful)
                {
                    Log.d("isSuccessful", response.code().toString())
                    val photoList = response.body()!!
                    adapterProfile.setupList(photoList)

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

    private fun setupList()
    {
        gridLayoutManager = GridLayoutManager(context, 2)
        recProfile.layoutManager = gridLayoutManager
        adapterProfile= PhotosAndFeedAdapter(context){
            bundle.putString("ImageID",it.id)
            findNavController().navigate(R.id.feedDetailFragment,bundle)
        }
        recProfile.adapter = adapterProfile

    }
    private fun bindView(view: View) {
        ivArrowBack=view.findViewById(R.id.arrow_back_profile)
        recProfile=view.findViewById(R.id.rec_profile)
        ivProfile=view.findViewById(R.id.iv_profile)
        profileUserName=view.findViewById(R.id.profile_username)
        toolbarUsername=view.findViewById(R.id.toolbar_username)
        profileUserBio=view.findViewById(R.id.profile_bio)
    }

}