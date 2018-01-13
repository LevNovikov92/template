package com.levnovikov.template.view

import android.databinding.ObservableField
import com.levnovikov.core_base.lifecycle.Lifecycle
import com.levnovikov.core_base.utils.ToastUtils
import com.levnovikov.template.R
import com.levnovikov.template.domain.UserReposRepo
import com.levnovikov.template.domain.model.UserRepo
import com.levnovikov.template.view.di.MainScope
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

/**
 * Author: lev.novikov
 * Date: 12/1/18.
 */

interface MainViewModel {
    val userName: ObservableField<String>
    val reposList: ObservableField<List<UserRepo>>

    fun onSearchClick()
}

@MainScope
class MainViewModelImpl @Inject constructor(
        private val toastUtils: ToastUtils,
        private val lifecycle: Lifecycle,
        private val userReposRepo: UserReposRepo) : MainViewModel {

    override val userName = ObservableField<String>()
    override val reposList = ObservableField<List<UserRepo>>()

    override fun onSearchClick() {
        userName.get()?.let {
            lifecycle.subscribeUntilDestroy(
                    userReposRepo.getUserRepos(it)
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe({ list ->
                                reposList.set(list)
                            }, { toastUtils.showShortToast(R.string.error_msg) }))
        }
    }
}