package com.asset.albumapplication.ui.fullPhoto

import android.os.Bundle
import com.asset.albumapplication.R
import com.asset.albumapplication.helpers.ConstantsExtra
import com.asset.albumapplication.ui.BaseActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_full_photo.*

class FullPhotoActivity : BaseActivity() {

    var photoUrl: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_photo)

        photoUrl = intent.getStringExtra(ConstantsExtra.PHOTO_URL)
        if (photoUrl != null){
            Picasso.get().load(photoUrl).placeholder(R.drawable.large_placeholder).into(fullImage)
        }else{
            showErrorMessage()
        }

    }

}
