package com.example.githubproject.ui.profiles

import androidx.lifecycle.MutableLiveData
import com.example.githubproject.ui.utils.AppState

class ProfilesContract {
    interface ViewModel {
        fun getLiveData(): MutableLiveData<AppState>
        fun getUserAccounts()
    }
}