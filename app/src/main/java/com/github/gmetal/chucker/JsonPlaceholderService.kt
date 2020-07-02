package com.github.gmetal.chucker

import retrofit2.Call
import retrofit2.http.GET

interface JsonPlaceholderService {
    @get:GET("/posts")
    val allPosts: Call<List<Post>>
}