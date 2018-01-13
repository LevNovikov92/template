package com.levnovikov.template.view

import android.databinding.BindingAdapter
import android.support.v7.widget.RecyclerView
import com.levnovikov.template.domain.model.UserRepo


/**
 * Created by lev.novikov
 * Date: 13/1/18.
 */

@BindingAdapter("bind:main_repos_list")
fun setData(view: RecyclerView, data: List<UserRepo>?) {
    val adapter = view.adapter
    if (data != null && adapter != null && adapter is Adapter) {
        adapter.setData(data)
    }
}