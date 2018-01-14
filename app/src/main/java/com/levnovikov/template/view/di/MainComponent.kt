package com.levnovikov.template.view.di

import android.content.Context
import com.levnovikov.api.di.ApiModule
import com.levnovikov.core_base.lifecycle.Lifecycle
import com.levnovikov.core_base.utils.ResourceProviderModule
import com.levnovikov.core_base.utils.ToastUtilsModule
import com.levnovikov.template.data.UserReposRepoImpl
import com.levnovikov.template.di.AppComponent
import com.levnovikov.template.domain.UserReposRepo
import com.levnovikov.template.view.MainActivity
import com.levnovikov.template.view.MainViewModel
import com.levnovikov.template.view.MainViewModelImpl
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Scope

/**
 * Created by lev.novikov
 * Date: 13/1/18.
 */

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class MainScope

@MainScope
@Component(dependencies = [AppComponent::class], modules = [
    MainComponent.MainBinders::class,
    MainComponent.MainModule::class])
interface MainComponent {

    @Module
    class MainModule(private val activity: MainActivity) {

        @Provides
        @MainScope
        fun provideContext(): Context = activity

        @MainScope
        @Provides
        fun provideLifecycle(): Lifecycle = activity

        @MainScope
        @Provides
        fun provideUserReposRepo(impl: UserReposRepoImpl): UserReposRepo = impl
    }

    @Module
    interface MainBinders {
        @Binds fun provideViewModel(impl: MainViewModelImpl): MainViewModel
    }

    fun inject(mainActivity: MainActivity)
}