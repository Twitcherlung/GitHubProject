package com.example.githubproject.data.use_case

import com.example.githubproject.domain.ProjectRepo
import com.example.githubproject.domain.entities.UserRepository
import com.example.githubproject.domain.usecase.ReposUseCase
import io.reactivex.rxjava3.core.Single

class RepositoryUseCaseImpl(private val api: ProjectRepo) : ReposUseCase {
    override fun getRepos(loginProfile: String): Single<List<UserRepository>> {
        return api.getRepos(loginProfile)
    }

}