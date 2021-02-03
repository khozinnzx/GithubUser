package com.example.githubuser.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.githubuser.data.FavoriteUser
import com.example.githubuser.data.FavoriteUserDao
import com.example.githubuser.data.UserDatabase
import com.example.githubuser.model.Users
import com.example.githubuser.retrofit.ApiClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailActivityViewModel(application: Application) : AndroidViewModel(application) {
    val detailUser = MutableLiveData<Users>()

    private var userDao: FavoriteUserDao?
    private var userDb: UserDatabase?

    init {
        userDb = UserDatabase.getDatabase(application)
        userDao = userDb?.favoriteUserDao()
    }

    fun setUserDetail(username: String) {
        ApiClient.getRetrofit.getUserDetail(username)
            .enqueue(object : Callback<Users> {
                override fun onResponse(
                    call: Call<Users>,
                    response: Response<Users>
                ) {
                    if (response.isSuccessful) {
                        detailUser.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<Users>, t: Throwable) {
                    Log.d("onFailure: ", t.message.toString())
                }

            })
    }

    fun getUserDetail(): LiveData<Users> {
        return detailUser
    }

    fun addToFavorite(username: String, id: Int, avatarUrl: String, url: String) {
        CoroutineScope(Dispatchers.IO).launch {
            var user = FavoriteUser(username, id, avatarUrl, url)
            userDao?.addtoFavorite(user)
        }

    }

    suspend fun checkUser(id: Int) = userDao?.checkUser(id)

    fun deleteFromFavorite(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            userDao?.deleteFromFavorite(id)
        }
    }
}