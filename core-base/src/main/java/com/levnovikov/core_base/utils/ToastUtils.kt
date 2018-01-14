package com.levnovikov.core_base.utils

import android.content.Context
import android.widget.Toast
import dagger.Binds
import dagger.Module
import javax.inject.Inject
import javax.inject.Named

/**
 * Created by lev.novikov
 * Date: 13/1/18.
 */

@Module
interface ToastUtilsModule {
    @Binds fun bindToastUtils(impl: ToastUtilsImpl): ToastUtils
}

interface ToastUtils {
    fun showShortToast(message: String)

    fun showShortToast(resId: Int)
}

class ToastUtilsImpl @Inject constructor(private val context: Context) : ToastUtils {

    private fun showToast(message: String, duration: Int) {
        Toast.makeText(context, message, duration).show()
    }

    override fun showShortToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun showShortToast(resId: Int) {
        Toast.makeText(context, resId, Toast.LENGTH_SHORT).show()
    }
}