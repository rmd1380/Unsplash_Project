package com.example.unsplashproject.fragments.searchfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.unsplashproject.R
import com.example.unsplashproject.adapter.SearchPhotoAdapter
import com.example.unsplashproject.viewmodels.searchviewmodels.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PhotoFragment : Fragment() {


    lateinit var gridLayoutManager: GridLayoutManager
    lateinit var recPhoto: RecyclerView
    lateinit var adapter: SearchPhotoAdapter
    private val viewModel: SearchViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_photo, container, false)
        init(view)
        return view
    }

    private fun init(view: View) {
        bindView(view)
        setupList()
        viewModel.mQuery.observe(viewLifecycleOwner){
            viewModel(it)
        }
    }

    private fun viewModel(query: String) {

        println("qqqqqqqqqqqqqqqqqq $query")
        viewModel.getLiveDataObserverPhoto(query).observe(viewLifecycleOwner) {
            println("itititi $it")
            if (it?.results != null) {
                adapter.setupList(it.results)
            } else {
                Toast.makeText(context, "Error in getting data", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun bindView(view: View) {
        recPhoto = view.findViewById(R.id.rec_photo)
    }

    private fun setupList() {
        gridLayoutManager = GridLayoutManager(context, 2)
        recPhoto.layoutManager = gridLayoutManager
        adapter = SearchPhotoAdapter(context) {
        }
        recPhoto.adapter = adapter
    }

}
//fun callApiPhoto() {
//    val serviceApi = ServiceBuilder.buildService(ServiceApi::class.java)
//    val requestCall = serviceApi.getPhotosBySearch(SearchFragment.query)
//    requestCall.enqueue(object : retrofit2.Callback<SearchResponse> {
//        override fun onResponse(
//            call: Call<SearchResponse>,
//            response: Response<SearchResponse>
//        ) {
//            if (response.isSuccessful) {
//                Log.d("isSuccessful", response.code().toString())
//                val photoList = response.body()!!
//                PhotoFragment.adapter.setupList(photoList.results)
//            } else {
//                Log.d("isFailed", response.code().toString())
//            }
//        }
//        override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
//            Log.d("onFailure", t.message.toString())
//        }
//    })
//}