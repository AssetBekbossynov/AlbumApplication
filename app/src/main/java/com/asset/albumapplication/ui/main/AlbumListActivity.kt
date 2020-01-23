package com.asset.albumapplication.ui.main

import android.nfc.tech.MifareUltralight.PAGE_SIZE
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.asset.albumapplication.R
import com.asset.albumapplication.ui.BaseActivity
import com.asset.albumapplication.util.Status
import com.asset.domain.entity.AlbumDomain
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf


class AlbumListActivity : BaseActivity() {

    val viewModel: AlbumListViewModel by inject { parametersOf(this) }

    private val albums = mutableListOf<AlbumDomain>()

    private var isLoading: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.getAlbum(start = albums.size)

        val adapter = AlbumAdapter(this, albums)

        viewModel.albumList.observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    it.data?.forEach { albums.add(it) }
                    adapter.notifyDataSetChanged()
                    isLoading = false
                    hideLoading()
                }
                Status.ERROR -> {
                    showErrorMessage(it.message)
                    isLoading = false
                    hideLoading()
                }
                Status.LOADING -> {
                    isLoading = true
                    showLoading()
                }
            }
        })

        rv.adapter = adapter
        rv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        val listener = ScrollListener(rv.layoutManager as LinearLayoutManager)

        rv.addOnScrollListener(listener)
    }

    fun showLoading(){
        progress.visibility = View.VISIBLE
    }

    fun hideLoading(){
        progress.visibility = View.GONE
    }

    inner class ScrollListener(val layoutManager: LinearLayoutManager): RecyclerView.OnScrollListener(){
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val visibleItemCount: Int = layoutManager.getChildCount()
            val totalItemCount: Int = layoutManager.getItemCount()
            val firstVisibleItemPosition: Int = layoutManager.findFirstVisibleItemPosition()

            if (!isLoading) {
                if (visibleItemCount + firstVisibleItemPosition >= totalItemCount &&
                    firstVisibleItemPosition >= 0 && totalItemCount >= PAGE_SIZE) {

                    viewModel.getAlbum(visibleItemCount, totalItemCount)
                }
            }
        }

        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
        }
    }
}
