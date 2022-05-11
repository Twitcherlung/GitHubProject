package com.example.githubproject.domain.entities

data class UserRepository(
    val id: Int,
    val name: String,
    val fullName: String,
    val description: String?,
    val language: String,
    val htmlUrl: String?
)