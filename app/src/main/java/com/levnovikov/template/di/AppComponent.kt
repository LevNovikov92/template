package com.levnovikov.template.di

import android.content.Context
import com.levnovikov.api.GitHubApi
import com.levnovikov.api.di.ApiModule
import com.levnovikov.core_base.utils.ResourceProviderModule
import com.levnovikov.core_base.utils.ToastUtils
import com.levnovikov.core_base.utils.ToastUtilsModule
import com.levnovikov.template.App
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by lev.novikov
 * Date: 14/1/18.
 */

@Singleton
@Component(modules = [
    AppComponent.AppModule::class,
    ResourceProviderModule::class,
    ApiModule::class,
    ToastUtilsModule::class])
interface AppComponent {

    @Module
    class AppModule(private val app: App) {

        @Singleton
        @Provides
        fun provideAppContext(): Context = app
    }

    fun toastUtils(): ToastUtils
    fun api(): GitHubApi
}