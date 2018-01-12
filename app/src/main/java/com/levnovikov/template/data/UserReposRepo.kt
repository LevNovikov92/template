package com.levnovikov.template.data

import com.levnovikov.api.GitHubApi
import com.levnovikov.template.domain.UserReposRepo
import com.levnovikov.template.domain.model.UserRepo
import io.reactivex.Single
import javax.inject.Inject

/**
 * Author: lev.novikov
 * Date: 12/1/18.
 */
class UserReposRepoImpl @Inject constructor(
        private val api: GitHubApi): UserReposRepo {

    override fun getUserRepos(userName: String): Single<List<UserRepo>> =
            api.userReposList(userName)
                    .map {
                        it.map {
                            it.run {
                                UserRepo(id, name, description, userName)
                            }
                        }
                    }
}