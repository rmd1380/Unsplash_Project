package com.example.unsplashproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.unsplashproject.fragments.FeedFragment
import com.example.unsplashproject.fragments.ProfileFragment
import com.example.unsplashproject.fragments.SearchFragment
import com.example.unsplashproject.fragments.TopicFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var bottomNavigationView: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        bindView()
        bottomNavigationView.setOnItemReselectedListener  {
            when(it.itemId)
            {
                R.id.it_feed->{
                    loadFragment(FeedFragment())
                    return@setOnItemReselectedListener
                }
                R.id.it_topic->{
                    loadFragment(TopicFragment())
                    return@setOnItemReselectedListener
                }
                R.id.it_search->{
                    loadFragment(SearchFragment())
                    return@setOnItemReselectedListener
                }
                R.id.it_profile->{
                    loadFragment(ProfileFragment())
                    return@setOnItemReselectedListener
                }
            }

        }
    }

    private fun bindView() {
        bottomNavigationView=findViewById(R.id.bottomNav)
    }

    private  fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container,fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

}