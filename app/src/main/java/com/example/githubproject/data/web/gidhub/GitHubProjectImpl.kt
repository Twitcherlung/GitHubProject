package com.example.githubproject.data.web.gidhub

import com.example.githubproject.domain.ProjectRepo
import com.example.githubproject.domain.entities.UserProfile
import com.example.githubproject.domain.entities.UserRepository
import io.reactivex.rxjava3.core.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class GitHubProjectImpl : ProjectRepo {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val api: GitHubApi = retrofit.create(GitHubApi::class.java)
    override fun getProfiles(): Single<List<UserProfile>> {
        return api.getProfiles().map { convertUserProfilesDtoToEntity(it) }
    }

    override fun getRepos(loginProfile: String): Single<List<UserRepository>> {
        return api.getRepos(loginProfile).map { convertUserReposDtoToEntity(it) }
    }
}