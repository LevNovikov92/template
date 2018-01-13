package com.levnovikov.api.di

import com.levnovikov.api.GitHubApi
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named

/**
 * Created by lev.novikov
 * Date: 12/1/18.
 */

const val BASE_URL = "BASE_URL"

@Module
class ApiModule {

    @Provides
    @Named(BASE_URL)
    fun provideBaseUrl(): String = "https://api.github.com"

    @Provides
    fun provideRetrofit(@Named(BASE_URL) baseUrl: String): Retrofit =
            createRetrofit(
                    baseUrl,
                    RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()),
                    GsonConverterFactory.create())

    private fun createRetrofit(
            baseUrl: String,
            callAdapterFactory: CallAdapter.Factory,
            converterFactory: Converter.Factory): Retrofit =
            Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addCallAdapterFactory(callAdapterFactory)
                    .addConverterFactory(converterFactory)
                    .build()

    @Provides fun provideGitHubApi(retrofit: Retrofit) : GitHubApi =
            retrofit.create(GitHubApi::class.java)
}