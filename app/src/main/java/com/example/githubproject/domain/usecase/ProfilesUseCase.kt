package com.example.githubproject.domain.usecase

import com.example.githubproject.domain.entities.UserProfile
import io.reactivex.rxjava3.core.Single

interface ProfilesUseCase {
    fun getProfiles(): Single<List<UserProfile>>
}