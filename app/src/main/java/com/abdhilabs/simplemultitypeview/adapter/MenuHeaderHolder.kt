package com.abdhilabs.simplemultitypeview.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_header.view.*

/**
 * Untuk menampilkan satu informasi, sehingga di dalamnya hanya terdapat satu TextView
 */

class MenuHeaderHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val itemHeader = itemView.tvHeaderItem

    fun bindContent(text: String) {
        itemHeader.text = text
    }
}