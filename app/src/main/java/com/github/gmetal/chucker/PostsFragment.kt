package com.github.gmetal.chucker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostsFragment : Fragment() {
    lateinit var postsAdapter: PostsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(R.layout.fragment_posts, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val postsList = view.findViewById<RecyclerView>(R.id.postsList)
        postsList.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        postsAdapter = PostsAdapter(requireContext())
        postsList.adapter = postsAdapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        RetrofitClientSingleton.getInstance(requireContext()).jsonPlaceholderService.allPosts.enqueue(
            object : Callback<List<Post>> {
                override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                    Toast.makeText(requireContext(), "Failure: $t", Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                    postsAdapter.data = response.body() ?: emptyList()
                    postsAdapter.notifyDataSetChanged()
                }
            }
        )
    }
}