package com.levnovikov.template

import android.databinding.ObservableField
import com.levnovikov.template.domain.UserReposRepo
import javax.inject.Inject

/**
 * Author: lev.novikov
 * Date: 12/1/18.
 */

interface MainViewModel {
    var userName: ObservableField<String>

    fun onSearchClick()
}

class MainViewModelImpl @Inject constructor(
        private val userReposRepo: UserReposRepo) : MainViewModel {

    override var userName = ObservableField<String>()

    override fun onSearchClick() {
        userReposRepo.getUserRepos(userName.get())
    }
}