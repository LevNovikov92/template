package com.levnovikov.api

import com.levnovikov.api.response.UserReposListResponse
import io.reactivex.Single
import retrofit2.http.GET

/**
 * Created by lev.novikov
 * Date: 12/1/18.
 */
interface GitHubApi {

    @GET("users/{user}/repos")
    fun userReposList(user: String): Single<UserReposListResponse>

}