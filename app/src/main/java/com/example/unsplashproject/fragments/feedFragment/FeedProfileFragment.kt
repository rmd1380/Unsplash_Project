package com.example.unsplashproject.fragments.feedFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.unsplashproject.R
import com.example.unsplashproject.adapter.FeedAdapter
import com.example.unsplashproject.model.FeedModel

class FeedProfileFragment : Fragment() {
    private lateinit var gridLayoutManager: GridLayoutManager
    private lateinit var ivArrowBack:ImageView
    private lateinit var recProfile:RecyclerView
    private lateinit var adapterProfile:FeedAdapter
    private var item = ArrayList<FeedModel>()
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
        item.clear()
        item.add(FeedModel(R.drawable.ic_launcher_background))
        item.add(FeedModel(R.drawable.ic_launcher_background))
        item.add(FeedModel(R.drawable.ic_launcher_background))
        item.add(FeedModel(R.drawable.ic_launcher_background))
        gridLayoutManager = GridLayoutManager(context, 2)
        recProfile.layoutManager = gridLayoutManager
        adapterProfile= FeedAdapter(context,item,){
            findNavController().navigate(R.id.feedDetailFragment)
        }
        recProfile.adapter = adapterProfile

        ivArrowBack.setOnClickListener{
            findNavController().popBackStack()
        }

    }
    private fun bindView(view: View) {
        ivArrowBack=view.findViewById(R.id.arrow_back_profile)
        recProfile=view.findViewById(R.id.rec_profile)


    }

}