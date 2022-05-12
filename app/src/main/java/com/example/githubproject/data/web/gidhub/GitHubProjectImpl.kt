package com.example.githubproject.data.web.gidhub

import com.example.githubproject.domain.ProjectRepo
import com.example.githubproject.domain.entities.UserProfile
import com.example.githubproject.domain.entities.UserRepository
import io.reactivex.rxjava3.core.Single


class GitHubProjectImpl(private val api: GitHubApi) : ProjectRepo {

    override fun getProfiles(): Single<List<UserProfile>> {
        return api.getProfiles().map { convertUserProfilesDtoToEntity(it) }
    }

    override fun getRepos(loginProfile: String): Single<List<UserRepository>> {
        return api.getRepos(loginProfile).map { convertUserReposDtoToEntity(it) }
    }
}