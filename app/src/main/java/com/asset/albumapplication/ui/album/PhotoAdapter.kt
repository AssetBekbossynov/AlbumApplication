package com.asset.albumapplication.ui.album

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.asset.albumapplication.R
import com.asset.data.util.Logger
import com.asset.domain.entity.PhotoDomain
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.photo_item.view.*

class PhotoAdapter(private val context: Context, private val list: List<PhotoDomain>) : RecyclerView.Adapter<PhotoAdapter.ViewHolder>() {

    private var itemId: Int = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.photo_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Picasso.get().load(list[position].thumbnailUrl).into(holder.itemView.image)
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
}