package com.example.unsplashproject.fragments.searchfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.unsplashproject.R
import com.example.unsplashproject.adapter.FeedAdapter
import com.example.unsplashproject.model.feed.FeedModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener

class SearchFragment : Fragment() {

    private lateinit var gridLayoutManager: GridLayoutManager
    private lateinit var adapterSearch: FeedAdapter
    private lateinit var recSearch: RecyclerView
    private lateinit var tabLayout: TabLayout
    private var item = ArrayList<FeedModel>()

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
        tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                if(tab.position==0)
                {
                    item.clear()
                    item.add(FeedModel(R.drawable.ic_launcher_background))
                    item.add(FeedModel(R.drawable.ic_launcher_background))
                    item.add(FeedModel(R.drawable.ic_launcher_background))
                    item.add(FeedModel(R.drawable.ic_launcher_background))
                    gridLayoutManager = GridLayoutManager(context, 2)
                    recSearch.layoutManager = gridLayoutManager
                    adapterSearch = FeedAdapter(context){

                    }
                    recSearch.adapter = adapterSearch
                }
                else if (tab.position==1)
                {

                }

            }
            override fun onTabUnselected(tab: TabLayout.Tab) {
            }

            override fun onTabReselected(tab: TabLayout.Tab) {}
        })

        tabLayout.selectTab(tabLayout.getTabAt(0))

    }

    private fun bindView(view: View) {
        recSearch = view.findViewById(R.id.rec_search)
        tabLayout = view.findViewById(R.id.tab_layout)
    }
}



