package com.example.pertemuan11.network

import com.example.pertemuan11.model.Users
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("users?page=2")
    fun getAllUsers(): Call<Users>
}