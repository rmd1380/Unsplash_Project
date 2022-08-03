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
import com.bumptech.glide.Glide
import com.example.unsplashproject.R
import com.example.unsplashproject.model.response.PhotoResponse
import com.example.unsplashproject.services.Service
import com.example.unsplashproject.services.ServiceBuilder
import retrofit2.Call
import retrofit2.Response


class FeedDetailFragment : Fragment() {

    private lateinit var ivProfile:ImageView
    private lateinit var ivArrowBack:ImageView
    private lateinit var mainPhoto:ImageView
    private lateinit var viewCount:TextView
    private lateinit var downloadCount:TextView
    private lateinit var publishDate:TextView
    private lateinit var camera:TextView
    private lateinit var userName:TextView
    var photoResponse=PhotoResponse()
    var bundle=Bundle()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view:View=inflater.inflate(R.layout.fragment_feed_detail, container, false)
        init(view)
        return view
    }

    private fun init(view: View) {
        bindView(view)
        callApi()
        ivProfile.setOnClickListener{
            bundle.putString("ImageUserNameProf",photoResponse.user?.username)
            bundle.putString("ImageIDProf",photoResponse.id)
            findNavController().navigate(R.id.feedProfileFragment,bundle)
        }
        ivArrowBack.setOnClickListener{
            findNavController().navigate(R.id.feedFragment)
        }
    }

    private fun callApi() {
        val photoDetailService= ServiceBuilder.buildService(Service::class.java)
        val requestCall=photoDetailService.getPhotoDetailById(requireArguments().getString("ImageID")!!)
        requestCall.enqueue(object :retrofit2.Callback<PhotoResponse>
        {
            override fun onResponse(
                call: Call<PhotoResponse>,
                response: Response<PhotoResponse>
            ) {
                if(response.isSuccessful)
                {
                    Log.d("isSuccessful", response.code().toString())
                    val photoDetail=response.body()!!
                    photoResponse=photoDetail
                    viewCount.text=photoDetail.views.toString()
                    downloadCount.text=photoDetail.downloads.toString()
                    publishDate.text=photoDetail.createdAt.toString()
                    camera.text=photoDetail.exif?.name.toString()
                    userName.text=photoDetail.user?.name.toString()
                    Glide
                        .with(context!!)
                        .load(photoDetail.urls?.regular)
                        .centerCrop()
                        .into(mainPhoto)

                    Glide
                        .with(context!!)
                        .load(photoDetail.user?.profileImage?.large)
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

    private fun bindView(view: View)
    {
        ivProfile=view.findViewById(R.id.feed_iv_profile_photo)
        ivArrowBack=view.findViewById(R.id.arrow_back)
        mainPhoto=view.findViewById(R.id.feed_iv_main_photo)
        viewCount=view.findViewById(R.id.tv_view_count)
        downloadCount=view.findViewById(R.id.tv_download_count)
        publishDate=view.findViewById(R.id.tv_publish_date)
        camera=view.findViewById(R.id.tv_camera)
        userName=view.findViewById(R.id.tv_profile_username)
    }


}