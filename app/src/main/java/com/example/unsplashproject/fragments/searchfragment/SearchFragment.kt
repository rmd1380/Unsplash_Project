package com.example.unsplashproject.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.unsplashproject.R

class SearchFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return view(inflater,container)
    }

    private fun view(inflater: LayoutInflater, container: ViewGroup?): View {
        val view: View = inflater.inflate(R.layout.fragment_search, container, false)
        return view
    }

}