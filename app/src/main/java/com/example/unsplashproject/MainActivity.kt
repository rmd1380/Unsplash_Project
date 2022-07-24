package com.example.unsplashproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.unsplashproject.fragments.feedFragment.FeedFragment
import com.example.unsplashproject.fragments.profilefragment.ProfileFragment
import com.example.unsplashproject.fragments.searchfragment.SearchFragment
import com.example.unsplashproject.fragments.topicfragment.TopicFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var bottomNavigationView: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadFragment(FeedFragment())
        init()
    }

    private fun init() {
        bindView()
        bottomNavigationView.setOnItemSelectedListener  {
            when(it.itemId)
            {
                R.id.it_feed->{
                    loadFragment(FeedFragment())
                }
                R.id.it_topic->{
                    loadFragment(TopicFragment())
                }
                R.id.it_search->{
                    loadFragment(SearchFragment())
                }
                R.id.it_profile->{
                    loadFragment(ProfileFragment())
                }
            }
            true
        }
    }

    private fun bindView() {
        bottomNavigationView=findViewById(R.id.bottomNav)
    }

    private  fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container,fragment)
        transaction.commit()
    }

}