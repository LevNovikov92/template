package com.levnovikov.api.client

import retrofit2.Retrofit

/**
 * Created by lev.novikov
 * Date: 12/1/18.
 */

class RetrofitProvider {

    fun provideRetrofit(baseUrl: String): Retrofit =
            Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .build()
}