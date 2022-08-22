package com.example.unsplashproject.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.unsplashproject.fragments.searchFragment.PhotoFragment
import com.example.unsplashproject.fragments.searchFragment.UserFragment


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