package com.abdhilabs.simplemultitypeview.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.abdhilabs.simplemultitypeview.R
import com.abdhilabs.simplemultitypeview.entity.MenuItem
import kotlinx.android.synthetic.main.item_menu.view.*

class MenuItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val itemName = itemView.findViewById(R.id.itemName) as TextView
    val itemPrice = itemView.itemPrice
    val itemCount = itemView.itemCount
    val btnAdd = itemView.itemAdd
    val btnDelete = itemView.itemRemove

    fun bindContent(menuItem: MenuItem) {
        itemCount.text = menuItem.count.toString()
        itemName.text = menuItem.name
        itemPrice.text = "Rp. ${menuItem.price}"
    }

}