package com.asset.albumapplication.ui

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity(){

    fun showErrorMessage(msg: String? = "Unexpected system error"){
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }
}