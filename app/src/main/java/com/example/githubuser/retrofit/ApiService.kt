package com.example.githubuser.retrofit

import com.example.githubuser.model.UserResponse
import com.example.githubuser.model.Users
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("users")
    @Headers("Authorization: token 03b19bf618ca054ef868ba663f9f1ba475db1807")
    fun getUsers(): Call<ArrayList<Users>>

    @GET("search/users")
    @Headers("Authorization: token 03b19bf618ca054ef868ba663f9f1ba475db1807")
    fun getSearchUser(
        @Query("q") query: String
    ): Call<UserResponse>

    @GET("users/{username}")
    @Headers("Authorization: token 03b19bf618ca054ef868ba663f9f1ba475db1807")
    fun getUserDetail(
        @Path("username") username: String
    ): Call<Users>

    @GET("users/{username}/followers")
    @Headers("Authorization: token 03b19bf618ca054ef868ba663f9f1ba475db1807")
    fun getFollowers(
        @Path("username") username: String
    ): Call<ArrayList<Users>>

    @GET("users/{username}/following")
    @Headers("Authorization: token 03b19bf618ca054ef868ba663f9f1ba475db1807")
    fun getFollowing(
        @Path("username") username: String
    ): Call<ArrayList<Users>>

}