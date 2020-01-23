package com.asset.albumapplication.ui.main

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.asset.albumapplication.R
import com.asset.albumapplication.helpers.ConstantsExtra
import com.asset.albumapplication.ui.album.PhotoListActivity
import com.asset.domain.entity.AlbumDomain
import kotlinx.android.synthetic.main.album_item.view.*

class AlbumAdapter(private val context: Context, private val list: List<AlbumDomain>) : RecyclerView.Adapter<AlbumAdapter.ViewHolder>() {

    private var itemId: Int = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.album_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.title.text = list[position].title
        itemId = list[position].id

        holder.itemView.setOnClickListener {
            val intent = Intent(context, PhotoListActivity::class.java)
            intent.putExtra(ConstantsExtra.ALBUM_ID, list[position].id)
            context.startActivity(intent)
        }
    }

    fun getItemId(): Int{
        return itemId
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
}