package com.example.githubproject.ui.utils

import com.example.githubproject.domain.entities.UserProfile
import com.example.githubproject.domain.entities.UserRepository

sealed class AppState {
    data class SuccessProfiles(val profiles: List<UserProfile>) : AppState()
    data class SuccessRepos(val repos: List<UserRepository>) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}