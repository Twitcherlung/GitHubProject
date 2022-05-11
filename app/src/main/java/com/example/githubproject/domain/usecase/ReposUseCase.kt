package com.example.githubproject.domain.usecase

import com.example.githubproject.domain.entities.UserRepository
import io.reactivex.rxjava3.core.Single

interface ReposUseCase {
    fun getRepos(loginProfile: String): Single<List<UserRepository>>
}