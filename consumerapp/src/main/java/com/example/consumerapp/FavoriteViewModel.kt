package com.example.consumerapp

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class FavoriteViewModel(application: Application): AndroidViewModel(application) {

    private var list = MutableLiveData<ArrayList<Users>>()

    fun setFavoriteUser(context: Context){
        val cursor = context.contentResolver.query(
            DatabaseContract.FavoriteUserColumns.CONTENT_URI,
            null,
            null,
            null,
            null
        )
        val listCoverted = MappingHelper.mapCursorToArraylist(cursor)
        list.postValue(listCoverted)
    }

    fun getFavoriteUser(): LiveData<ArrayList<Users>>{
        return list
    }
}