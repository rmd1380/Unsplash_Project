package com.example.unsplashproject.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.unsplashproject.fragments.searchfragment.PhotoFragment
import com.example.unsplashproject.fragments.searchfragment.SearchFragment
import com.example.unsplashproject.fragments.searchfragment.UserFragment
import com.example.unsplashproject.model.response.PhotoResponse


class ViewPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {

        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                PhotoFragment()
            }
            1 -> {
                UserFragment()
            }
            else -> {
                Fragment()
            }
        }
    }
}