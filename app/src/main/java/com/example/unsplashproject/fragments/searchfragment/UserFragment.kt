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
import com.example.unsplashproject.adapter.SearchUserAdapter
import com.example.unsplashproject.viewmodels.searchviewmodels.SearchViewModel

class UserFragment : Fragment() {

    lateinit var gridLayoutManager: GridLayoutManager
    lateinit var recUser: RecyclerView
    lateinit var adapter: SearchUserAdapter
    private val viewModel: SearchViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_user, container, false)
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
        viewModel.getLiveDataObserverUser(query).observe(viewLifecycleOwner) {
            println("itititi $it")
            if (it != null) {
                adapter.setupList(it.results)
            } else {
                Toast.makeText(context, "Error in getting data", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun bindView(view: View) {
        recUser = view.findViewById(R.id.rec_user)
    }

    private fun setupList() {
        gridLayoutManager = GridLayoutManager(context, 2)
        recUser.layoutManager = gridLayoutManager
        adapter = SearchUserAdapter(context) {
        }
        recUser.adapter = adapter
    }

}


//fun callApiUser() {
//    val serviceApi = ServiceBuilder.buildService(ServiceApi::class.java)
//    val requestCall = serviceApi.getUsersBySearch(SearchFragment.query)
//    requestCall.enqueue(object : retrofit2.Callback<SearchResponse> {
//        override fun onResponse(
//            call: Call<SearchResponse>,
//            response: Response<SearchResponse>
//        ) {
//            if (response.isSuccessful) {
//                Log.d("isSuccessful", response.code().toString())
//                val userList = response.body()!!
//                UserFragment.adapter.setupList(userList.results)
//            } else {
//                Log.d("isFailed", response.code().toString())
//            }
//        }
//        override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
//            Log.d("onFailure", t.message.toString())
//        }
//    })
//}