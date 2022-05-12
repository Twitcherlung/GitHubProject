package com.example.githubproject.data.di

import com.example.githubproject.data.use_case.ProfilesUseCaseImpl
import com.example.githubproject.data.use_case.RepositoryUseCaseImpl
import com.example.githubproject.data.web.gidhub.GitHubApi
import com.example.githubproject.data.web.gidhub.GitHubProjectImpl
import com.example.githubproject.domain.ProjectRepo
import com.example.githubproject.domain.usecase.ProfilesUseCase
import com.example.githubproject.domain.usecase.ReposUseCase
import com.example.githubproject.ui.profile_details.ProfileDetailsViewModel
import com.example.githubproject.ui.profile_details.ProfileDetailsViewModelAbs
import com.example.githubproject.ui.profiles.ProfilesViewModel
import com.example.githubproject.ui.profiles.ProfilesViewModelAbs
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


val appModule = module {

    single<String>(named("api_url")) { "https://api.github.com/" }

    single {
        Retrofit.Builder()
            .baseUrl(get<String>(named("api_url")))
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(get())
            .build()
    }

    single<GitHubApi> { get<Retrofit>().create(GitHubApi::class.java) }

    single<ProjectRepo> { GitHubProjectImpl(get()) }

    single<ProfilesUseCase> { ProfilesUseCaseImpl(get()) }

    single<ReposUseCase> { RepositoryUseCaseImpl(get()) }

    factory<Converter.Factory> { GsonConverterFactory.create() }

    viewModel<ProfilesViewModelAbs> { ProfilesViewModel(get()) }
    viewModel<ProfileDetailsViewModelAbs> { ProfileDetailsViewModel(get()) }
}