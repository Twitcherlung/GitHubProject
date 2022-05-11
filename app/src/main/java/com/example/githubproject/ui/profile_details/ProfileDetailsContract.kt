package com.example.githubproject.ui.profile_details

import androidx.lifecycle.MutableLiveData
import com.example.githubproject.ui.utils.AppState

class ProfileDetailsContract {
    interface ViewModel {
        fun getLiveData(): MutableLiveData<AppState>
        fun getRepos(loginProfile: String)
    }
}