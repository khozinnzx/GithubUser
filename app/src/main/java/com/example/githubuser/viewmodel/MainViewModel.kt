package com.example.githubuser.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubuser.model.UserResponse
import com.example.githubuser.model.Users
import com.example.githubuser.retrofit.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    val listUsers = MutableLiveData<ArrayList<Users>>()


    fun loadSearch(query: String) {
        ApiClient.getRetrofit.getSearchUser(query).enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful) {
                    listUsers.postValue(response.body()?.items)
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Log.d("onFailure: ", t.message.toString())
            }

        })
    }

    fun getListData(): LiveData<ArrayList<Users>> {
        return listUsers
    }

}