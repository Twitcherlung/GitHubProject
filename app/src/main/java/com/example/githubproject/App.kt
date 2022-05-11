package com.example.githubproject

import android.app.Application
import android.content.Context
import com.example.githubproject.data.use_case.ProfilesUseCaseImpl
import com.example.githubproject.data.use_case.RepositoryUseCaseImpl
import com.example.githubproject.data.web.gidhub.GitHubProjectImpl
import com.example.githubproject.domain.ProjectRepo
import com.example.githubproject.domain.usecase.ProfilesUseCase
import com.example.githubproject.domain.usecase.ReposUseCase

class App : Application() {
    private val gitHubApi: ProjectRepo by lazy {
        GitHubProjectImpl()
    }
    val profilesUseCaseImpl: ProfilesUseCase by lazy {
        ProfilesUseCaseImpl(app.gitHubApi)
    }
    val repositoryUseCaseImpl: ReposUseCase by lazy {
        RepositoryUseCaseImpl(app.gitHubApi)
    }
}

val Context.app: App
    get() = this.applicationContext as App