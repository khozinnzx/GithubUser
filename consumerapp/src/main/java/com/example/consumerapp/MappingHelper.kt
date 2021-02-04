package com.example.consumerapp

import android.database.Cursor

object MappingHelper {

    fun mapCursorToArraylist(cursor: Cursor?): ArrayList<Users>{
        val list = ArrayList<Users>()

        if (cursor != null){
            while (cursor.moveToNext()){
                val id = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.FavoriteUserColumns.ID))
                val username = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FavoriteUserColumns.USERNAME))
                val avatarUrl = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FavoriteUserColumns.AVATAR_URL))
                val url = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FavoriteUserColumns.URL))
                list.add(
                    Users(
                        username,
                        id,
                        avatarUrl,
                        url
                    )
                )
            }
        }
        return list
    }
}