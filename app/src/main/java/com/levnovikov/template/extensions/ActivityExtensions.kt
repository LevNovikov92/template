package com.levnovikov.template.extensions

import android.support.v7.app.AppCompatActivity
import com.levnovikov.template.App
import com.levnovikov.template.di.AppComponent

/**
 * Created by lev.novikov
 * Date: 14/1/18.
 */

fun AppCompatActivity.getAppComponent(): AppComponent {
    application.let {
        if (it is App) {
            return it.getAppComponent()
        }
    }
    throw UnsupportedOperationException("App component not found")
}