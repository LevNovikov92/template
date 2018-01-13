package com.levnovikov.core_base.utils

import android.content.res.Resources
import dagger.Binds
import dagger.Module
import javax.inject.Inject

/**
 * Created by lev.novikov
 * Date: 13/1/18.
 */

@Module
interface ResourceProviderModule {
    @Binds
    fun bindResourceProvider(impl: ResourceProviderImpl): ResourceProvider
}

interface ResourceProvider {
    fun getString(resId: Int): String
}

class ResourceProviderImpl @Inject constructor(private val res: Resources) : ResourceProvider {
    override fun getString(resId: Int) = res.getString(resId)!!
}