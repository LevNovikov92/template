package com.levnovikov.template.domain

import com.levnovikov.template.domain.model.UserRepo
import io.reactivex.Single

/**
 * Author: lev.novikov
 * Date: 12/1/18.
 */
interface UserReposRepo {

    fun getUserRepos(userName: String): Single<List<UserRepo>>
}