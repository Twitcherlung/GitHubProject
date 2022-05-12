package com.example.githubproject.domain

import com.example.githubproject.domain.entities.UserProfile
import com.example.githubproject.domain.entities.UserRepository
import io.reactivex.rxjava3.core.Single

interface ProjectRepo {
    fun getProfiles(): Single<List<UserProfile>>
    fun getRepos(loginProfile: String): Single<List<UserRepository>>
}