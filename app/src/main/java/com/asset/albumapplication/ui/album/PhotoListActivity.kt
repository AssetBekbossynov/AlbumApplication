package com.asset.albumapplication.ui.album

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.asset.albumapplication.R
import com.asset.albumapplication.helpers.ConstantsExtra
import com.asset.albumapplication.ui.BaseActivity
import com.asset.albumapplication.util.Status
import com.asset.domain.entity.PhotoDomain
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf


class PhotoListActivity : BaseActivity() {

    private val photoList = mutableListOf<PhotoDomain>()

    private val viewModel: PhotoListViewModel by inject { parametersOf(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (intent.hasExtra(ConstantsExtra.ALBUM_ID))
            viewModel.getPhoto(intent.getIntExtra(ConstantsExtra.ALBUM_ID, -1))
        else
            showErrorMessage()

        val adapter = PhotoAdapter(this, photoList)

        viewModel.photoList.observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    it.data?.forEach { photoList.add(it) }
                    adapter.notifyDataSetChanged()
                }
                Status.ERROR -> {
                    showErrorMessage(it.message)
                }
                Status.LOADING -> {  }
            }
        })

        rv.adapter = adapter
        rv.layoutManager = GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false)
    }
}
