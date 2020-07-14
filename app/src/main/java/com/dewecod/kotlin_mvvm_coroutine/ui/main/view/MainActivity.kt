package com.dewecod.kotlin_mvvm_coroutine.ui.main.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.dewecod.kotlin_mvvm_coroutine.R
import com.dewecod.kotlin_mvvm_coroutine.data.api.ApiHelper
import com.dewecod.kotlin_mvvm_coroutine.data.api.RetrofitBuilder
import com.dewecod.kotlin_mvvm_coroutine.data.model.User
import com.dewecod.kotlin_mvvm_coroutine.ui.main.adapter.MainAdapter
import com.dewecod.kotlin_mvvm_coroutine.ui.main.viewmodel.MainViewModel
import com.dewecod.kotlin_mvvm_coroutine.ui.main.viewmodel.ViewModelFactory
import com.dewecod.kotlin_mvvm_coroutine.util.Status
import com.dewecod.kotlin_mvvm_coroutine.util.Status.ERROR
import com.dewecod.kotlin_mvvm_coroutine.util.Status.SUCCESS
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViewModel()
        setupUI()
        setupObservers()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(
                this,
                ViewModelFactory(ApiHelper(RetrofitBuilder.apiService))
        ).get(MainViewModel::class.java)
    }

    private fun setupUI() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MainAdapter(arrayListOf())
        recyclerView.addItemDecoration(
                DividerItemDecoration(
                        recyclerView.context,
                        (recyclerView.layoutManager as LinearLayoutManager).orientation
                )
        )
        recyclerView.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.getUserList().observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    SUCCESS -> {
                        recyclerView.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        resource.data?.let { users -> retrieveList(users) }
                    }
                    ERROR -> {
                        recyclerView.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        progressBar.visibility = View.VISIBLE
                        recyclerView.visibility = View.GONE
                    }
                }
            }
        })
    }

    private fun retrieveList(users: List<User>) {
        adapter.apply {
            addUserList(users)
            notifyDataSetChanged()
        }
    }
}