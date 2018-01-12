package com.levnovikov.api.di

import com.levnovikov.api.GitHubApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named

/**
 * Created by lev.novikov
 * Date: 12/1/18.
 */

const val BASE_URL = "BASE_URL"

@Module
class ApiModule {

    @Provides
    fun provideRetrofit(@Named(BASE_URL) baseUrl: String): Retrofit =
            Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .build()

    @Provides fun provideGitHubApi(retrofit: Retrofit) : GitHubApi =
            retrofit.create(GitHubApi::class.java)
}