package com.example.unsplashproject.fragments.searchfragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.unsplashproject.R
import com.example.unsplashproject.adapter.ViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class SearchFragment : Fragment() {

    private lateinit var adapterSearch: ViewPagerAdapter
    private lateinit var recSearch: RecyclerView
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager2: ViewPager2
    private lateinit var etSearch: TextInputLayout
    private var photoFragment=PhotoFragment()
    private var userFragment=UserFragment()
    companion object{
        var query:String = "cat"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return view(inflater, container)
    }

    private fun view(inflater: LayoutInflater, container: ViewGroup?): View {
        val view: View = inflater.inflate(R.layout.fragment_search, container, false)
        init(view)
        return view
    }

    private fun init(view: View) {
        bindView(view)
        adapterSearch = ViewPagerAdapter(context as FragmentActivity)
        viewPager2.adapter = adapterSearch
        TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
            tab.text = when (position) {
                0 -> {
                    "Photos"
                }
                1 -> {
                    "Users"
                }
                else -> {
                    ""
                }
            }
        }.attach()
        etSearch.editText?.setOnEditorActionListener { textView, i, event ->
            if (i == EditorInfo.IME_ACTION_DONE) {
                query = etSearch.editText?.text.toString()
                photoFragment.callApiPhoto()
                userFragment.callApiUser()
                false
            } else {
                true
            }
        }
    }
    private fun bindView(view: View) {
        recSearch = view.findViewById(R.id.rec_search)
        tabLayout = view.findViewById(R.id.tab_layout)
        viewPager2 = view.findViewById(R.id.viewpager2)
        etSearch = view.findViewById(R.id.et_search)
    }
}



