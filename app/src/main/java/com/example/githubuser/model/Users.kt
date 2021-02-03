package com.example.githubuser.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Users(
    val login: String,
    val id: Int,
    val avatar_url: String,
    val url: String?,
    val html_url: String?,
    val followers_url: String?,
    val following_url: String?,
    val gists_url: String?,
    val starred_url: String?,
    val subscriptions_url: String?,
    val organizations_url: String?,
    val repos_url: String?,
    val events_url: String?,
    val received_events_url: String?,
    val type: String?,
    val site_admin: Boolean?,
    val name: String?,
    val company: String?,
    val blog: String?,
    val location: String?,
    val email: String?,
    val hireable: String?,
    val bio: String?,
    val twitter_username: String?,
    val public_repos: String?,
    val public_gists: String?,
    val followers: Int,
    val following: Int,
    val created_at: String,
    val update_at: String,
) : Parcelable