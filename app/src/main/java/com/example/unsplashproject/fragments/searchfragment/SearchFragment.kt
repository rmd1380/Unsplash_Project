package com.example.unsplashproject.fragments.searchfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.unsplashproject.R
import com.example.unsplashproject.adapter.ViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class SearchFragment : Fragment() {

    private lateinit var adapterSearch: ViewPagerAdapter
    private lateinit var recSearch: RecyclerView
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager2: ViewPager2

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return view(inflater, container)
    }

    private fun view(inflater: LayoutInflater, container: ViewGroup?): View {
        val view: View = inflater.inflate(R.layout.fragment_search, container, false)
        init(view)
        return view
    }

    private fun init(view: View) {
        bindView(view)

        adapterSearch= ViewPagerAdapter(context!! as FragmentActivity)
        viewPager2.adapter=adapterSearch
        TabLayoutMediator(tabLayout,viewPager2){tab,position ->
            tab.text=when(position)
            {
                0->{"Photos"}
                1->{"Users"}
                else ->{""}
            }
        }.attach()
    }

    private fun bindView(view: View) {
        recSearch = view.findViewById(R.id.rec_search)
        tabLayout = view.findViewById(R.id.tab_layout)
        viewPager2=view.findViewById(R.id.viewpager2)
    }
}



