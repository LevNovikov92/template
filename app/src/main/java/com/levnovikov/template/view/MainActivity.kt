package com.levnovikov.template.view

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.levnovikov.core_base.lifecycle.LifecycleActivity
import com.levnovikov.template.R
import com.levnovikov.template.databinding.ActivityMainScreenBinding
import com.levnovikov.template.domain.model.UserRepo
import com.levnovikov.template.view.di.DaggerMainComponent
import com.levnovikov.template.view.di.MainComponent
import javax.inject.Inject

class MainActivity : LifecycleActivity() {

    @Inject lateinit var viewModel: MainViewModel

    private var adapter: Adapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupDependencyInjection()
        setupDataBindings()
        setupUI()
    }

    private fun setupDependencyInjection() {
        val component: MainComponent = DaggerMainComponent.builder()
                .mainModule(MainComponent.MainModule(this))
                .build()
        component.inject(this)
    }

    private fun setupDataBindings() {
        val binding = DataBindingUtil.setContentView<ActivityMainScreenBinding>(this, R.layout.activity_main_screen)
        binding.run {
            vm = viewModel
        }
    }

    private fun setupUI() {
        val rc = findViewById<RecyclerView>(R.id.repos_list)
        rc.layoutManager = LinearLayoutManager(this)
        adapter = Adapter(layoutInflater)
        rc.adapter = adapter
    }
}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val title: TextView = view.findViewById(R.id.title)

    fun bind(title: String) {
        this.title.text = title
    }
}

class Adapter(private val inflater: LayoutInflater) : RecyclerView.Adapter<ViewHolder>() {

    private var data: List<UserRepo>? = null

    fun setData(data: List<UserRepo>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = data?.size ?: 0


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        data?.let {
            holder.bind(it[position].name)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder =
            ViewHolder(inflater.inflate(R.layout.repos_list_item, parent, false))
}
