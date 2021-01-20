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

class DetailActivityViewModel: ViewModel() {
    val detailUser = MutableLiveData<Users>()

    fun setUserDetail(username: String){
        ApiClient.getRetrofit.getUserDetail(username)
            .enqueue(object :Callback<Users>{
                override fun onResponse(
                    call: Call<Users>,
                    response: Response<Users>
                ) {
                    if (response.isSuccessful){
                        detailUser.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<Users>, t: Throwable) {
                    Log.d("onFailure: ", t.message.toString())
                }

            })
    }

    fun getUserDetail(): LiveData<Users>{
        return detailUser
    }
}