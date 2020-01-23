package com.asset.albumapplication.ui.album

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getDrawable
import androidx.recyclerview.widget.RecyclerView
import com.asset.albumapplication.R
import com.asset.albumapplication.helpers.ConstantsExtra
import com.asset.albumapplication.ui.fullPhoto.FullPhotoActivity
import com.asset.domain.entity.PhotoDomain
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.photo_item.view.*

class PhotoAdapter(private val context: Context, private val list: List<PhotoDomain>) : RecyclerView.Adapter<PhotoAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.photo_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getDrawable(context, R.drawable.mini_placeholder)?.let {
            Picasso.get().load(list[position].thumbnailUrl).placeholder(
                it
            ).into(holder.itemView.image)
        }

        holder.itemView.setOnClickListener {
            val intent = Intent(context, FullPhotoActivity::class.java)
            intent.putExtra(ConstantsExtra.PHOTO_URL, list[position].url)
            context.startActivity(intent)
        }
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
}