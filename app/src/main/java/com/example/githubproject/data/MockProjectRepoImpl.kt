package com.example.githubproject.data

import com.example.githubproject.domain.ProjectRepo
import com.example.githubproject.domain.entities.UserProfile
import com.example.githubproject.domain.entities.UserRepository

class MockProjectRepoImpl : ProjectRepo {
    private val repoList: List<UserRepository> = listOf(
        UserRepository(
            id = 3899305,
            name = "githubproject",
            fullName = "Twitcherlung/GitHubProject",
            htmlUrl = "https://github.com/Gunixun/mvp_mvvm",
            description = "null",
            language = "Kotlin"
        ),
        UserRepository(
            id = 3899305,
            name = "githubproject",
            fullName = "Twitcherlung/GitHubProject",
            htmlUrl = "https://github.com/Gunixun/mvp_mvvm",
            description = "null",
            language = "Kotlin"
        ),
        UserRepository(
            id = 3899305,
            name = "githubproject",
            fullName = "Twitcherlung/GitHubProject",
            htmlUrl = "https://github.com/Gunixun/mvp_mvvm",
            description = "null",
            language = "Kotlin"
        ),
        UserRepository(
            id = 3899305,
            name = "githubproject",
            fullName = "Twitcherlung/GitHubProject",
            htmlUrl = "https://github.com/Gunixun/mvp_mvvm",
            description = "null",
            language = "Kotlin"
        ),
    )
    private val profiles: List<UserProfile> = listOf(
        UserProfile(
            id = 86185919,
            login = "Twitcherlung",
            name = null,
            avatarUrl = "https://avatars.githubusercontent.com/u/86185919?v=4",
            email = null
        ),
        UserProfile(
            id = 86185919,
            login = "Twitcherlung",
            name = null,
            avatarUrl = "https://avatars.githubusercontent.com/u/86185919?v=4",
            email = null
        ),
        UserProfile(
            id = 86185919,
            login = "Twitcherlung",
            name = null,
            avatarUrl = "https://avatars.githubusercontent.com/u/86185919?v=4",
            email = null
        ),
    )
}