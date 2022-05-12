package com.example.githubproject.ui.profile_details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubproject.domain.usecase.ReposUseCase
import com.example.githubproject.ui.utils.AppState
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.subscribeBy

class ProfileDetailsViewModel(
    private val repositoryUseCaseImpl: ReposUseCase
) : ProfileDetailsViewModelAbs() {

    private val liveData: MutableLiveData<AppState> = MutableLiveData()
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun getLiveData(): MutableLiveData<AppState> = liveData

    override fun getRepos(loginProfile: String) {
        liveData.postValue(AppState.Loading)

        compositeDisposable.add(
            repositoryUseCaseImpl
                .getRepos(loginProfile)
                .subscribeBy(
                    onSuccess = {
                        liveData.postValue(AppState.SuccessRepos(it))
                    },
                    onError = {
                        liveData.postValue(AppState.Error(it))
                    })
        )
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}