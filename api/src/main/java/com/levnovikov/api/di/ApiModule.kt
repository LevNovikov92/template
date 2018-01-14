package com.levnovikov.api.di

import com.levnovikov.api.GitHubApi
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

/**
 * Created by lev.novikov
 * Date: 12/1/18.
 */

const val BASE_URL = "BASE_URL"

@Module(includes = [HttpClientModule::class])
class ApiModule {

    @Provides
    @Named(BASE_URL)
    fun provideBaseUrl(): String = "https://api.github.com"

    @Singleton
    @Provides
    fun provideRetrofit(@Named(BASE_URL) baseUrl: String, okHttpClient: OkHttpClient): Retrofit =
            createRetrofit(
                    baseUrl,
                    okHttpClient,
                    RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()),
                    GsonConverterFactory.create())

    private fun createRetrofit(
            baseUrl: String,
            client: OkHttpClient,
            callAdapterFactory: CallAdapter.Factory,
            converterFactory: Converter.Factory): Retrofit =
            Retrofit.Builder()
                    .client(client)
                    .baseUrl(baseUrl)
                    .addCallAdapterFactory(callAdapterFactory)
                    .addConverterFactory(converterFactory)
                    .build()

    @Singleton
    @Provides
    fun provideGitHubApi(retrofit: Retrofit) : GitHubApi =
            retrofit.create(GitHubApi::class.java)
}

@Module
class HttpClientModule {

    @Singleton
    @Provides
    fun okHttpClient(): OkHttpClient {
        return OkHttpClient()
    }
}