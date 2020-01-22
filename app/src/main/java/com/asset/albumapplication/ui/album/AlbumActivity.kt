package com.asset.albumapplication.ui.album

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.asset.albumapplication.R
import com.asset.albumapplication.helpers.ConstantsExtra
import com.asset.domain.entity.PhotoDomain
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class AlbumActivity : AppCompatActivity() {

    private val photoList = mutableListOf<PhotoDomain>()

    private val viewModel: PhotoListViewModel by inject { parametersOf(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (intent.hasExtra(ConstantsExtra.ALBUM_ID))
            viewModel.getPhoto(intent.getIntExtra(ConstantsExtra.ALBUM_ID, -1))
        else
            Toast.makeText(this, "Server Error", Toast.LENGTH_LONG).show()

        val adapter = PhotoAdapter(this, photoList)

        viewModel.photoList.observe(this, Observer {
            photoList.add(it)
            adapter.notifyDataSetChanged()
        })

        rv.adapter = adapter
        rv.layoutManager = GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false)
    }
}
