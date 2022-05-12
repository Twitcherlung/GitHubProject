package com.example.githubproject.data.use_case

import com.example.githubproject.domain.ProjectRepo
import com.example.githubproject.domain.entities.UserProfile
import com.example.githubproject.domain.usecase.ProfilesUseCase
import io.reactivex.rxjava3.core.Single

class ProfilesUseCaseImpl(private val api: ProjectRepo) : ProfilesUseCase {
    override fun getProfiles(): Single<List<UserProfile>> {
        return api.getProfiles()
    }
}