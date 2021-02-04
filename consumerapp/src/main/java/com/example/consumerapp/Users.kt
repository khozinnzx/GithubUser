package com.example.consumerapp

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Users(
    val login: String,
    val id: Int,
    val avatar_url: String,
    val url: String?,
) : Parcelable