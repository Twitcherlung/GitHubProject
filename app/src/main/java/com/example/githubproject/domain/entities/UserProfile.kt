package com.example.githubproject.domain.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserProfile(
    val id: Int,
    val login: String,
    val name: String?,
    val email: String?,
    val avatarUrl: String
) : Parcelable
