package com.example.githubproject.data.web.gidhub

import com.example.githubproject.data.web.gidhub.dto.UserProfileDto
import com.example.githubproject.data.web.gidhub.dto.UserRepoDto
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubApi {
    @GET("users")
    fun getProfiles(): Single<List<UserProfileDto>>

    @GET("users/{username}/repos")
    fun getRepos(
        @Path("username") username: String
    ): Single<List<UserRepoDto>>
}