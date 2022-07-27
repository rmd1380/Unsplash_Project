package com.example.unsplashproject.fragments.feedFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.fragment.findNavController
import com.example.unsplashproject.R


class FeedDetailFragment : Fragment() {

    private lateinit var ivProfile:ImageView
    private lateinit var ivArrowBack:ImageView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view:View=inflater.inflate(R.layout.fragment_feed_detail, container, false)
        bindView(view)
        return view
    }
    private fun bindView(view: View)
    {
        ivProfile=view.findViewById(R.id.feed_iv_detail_profile)
        ivArrowBack=view.findViewById(R.id.arrow_back)
        ivProfile.setOnClickListener{
            findNavController().navigate(R.id.feedProfileFragment)
        }
        ivArrowBack.setOnClickListener{
            findNavController().navigate(R.id.feedFragment)
        }
    }

}