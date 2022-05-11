package com.example.githubproject.data.web.gidhub.dto

data class UserRepoDto(
    val id: Int,
    val name: String,
    val fullName: String,
    val htmlUrl: String,
    val description: String?,
    val language: String?
)