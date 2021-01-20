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
    @Headers("Authorization: token 7b3566640f1a11831585bdd4293f5239150174ac")
    fun getUsers(): Call<ArrayList<Users>>

    @GET("search/users")
    @Headers("Authorization: token 7b3566640f1a11831585bdd4293f5239150174ac")
    fun getSearchUser(
        @Query("q") query: String
    ): Call<UserResponse>

    @GET("users/{username}")
    @Headers("Authorization: token 7b3566640f1a11831585bdd4293f5239150174ac")
    fun getUserDetail(
        @Path("username") username: String
    ): Call<Users>

    @GET("users/{username}/followers")
    @Headers("Authorization: token 7b3566640f1a11831585bdd4293f5239150174ac")
    fun getFollowers(
        @Path("username") username: String
    ): Call<ArrayList<Users>>

    @GET("users/{username}/following")
    @Headers("Authorization: token 7b3566640f1a11831585bdd4293f5239150174ac")
    fun getFollowing(
        @Path("username") username: String
    ): Call<ArrayList<Users>>

}