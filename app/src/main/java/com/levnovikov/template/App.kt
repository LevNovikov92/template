package com.levnovikov.template

import android.app.Application
import com.levnovikov.template.di.AppComponent
import com.levnovikov.template.di.DaggerAppComponent

/**
 * Created by lev.novikov
 * Date: 14/1/18.
 */
class App : Application() {

    lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()
        setupDependencyInjection()
    }

    private fun setupDependencyInjection() {
        component = DaggerAppComponent.builder()
                .appModule(AppComponent.AppModule(this))
                .build()
    }

    fun getAppComponent() = component
}