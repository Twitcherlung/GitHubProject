package com.example.githubproject.data.web.gidhub

import com.example.githubproject.data.web.gidhub.dto.UserProfileDto
import com.example.githubproject.data.web.gidhub.dto.UserRepoDto
import com.example.githubproject.domain.entities.UserProfile
import com.example.githubproject.domain.entities.UserRepository

fun convertUserProfilesDtoToEntity(profilesList: List<UserProfileDto>): List<UserProfile> {
    val res: MutableList<UserProfile> = mutableListOf()
    for (profile in profilesList) {
        res.add(
            UserProfile(
                id = profile.id,
                login = profile.login,
                name = profile.name,
                avatarUrl = profile.avatarUrl,
                email = profile.email
            )
        )
    }
    return res
}

fun convertUserReposDtoToEntity(reposList: List<UserRepoDto>): List<UserRepository> {
    val res: MutableList<UserRepository> = mutableListOf()
    for (profile in reposList) {
        res.add(
            UserRepository(
                id = profile.id,
                name = profile.name,
                fullName = profile.fullName,
                htmlUrl = profile.htmlUrl,
                description = profile.description,
                language = profile.language
            )
        )
    }
    return res
}
