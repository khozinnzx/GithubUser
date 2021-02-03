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
    @Headers("Authorization: token 22f8ec6716ede5aa67a6518f157b58009929496f")
    fun getUsers(): Call<ArrayList<Users>>

    @GET("search/users")
    @Headers("Authorization: token 22f8ec6716ede5aa67a6518f157b58009929496f")
    fun getSearchUser(
        @Query("q") query: String
    ): Call<UserResponse>

    @GET("users/{username}")
    @Headers("Authorization: token 22f8ec6716ede5aa67a6518f157b58009929496f")
    fun getUserDetail(
        @Path("username") username: String
    ): Call<Users>

    @GET("users/{username}/followers")
    @Headers("Authorization: token 22f8ec6716ede5aa67a6518f157b58009929496f")
    fun getFollowers(
        @Path("username") username: String
    ): Call<ArrayList<Users>>

    @GET("users/{username}/following")
    @Headers("Authorization: token 22f8ec6716ede5aa67a6518f157b58009929496f")
    fun getFollowing(
        @Path("username") username: String
    ): Call<ArrayList<Users>>

}