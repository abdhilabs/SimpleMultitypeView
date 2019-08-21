package com.abdhilabs.simplemultitypeview.adapter

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abdhilabs.simplemultitypeview.R
import com.abdhilabs.simplemultitypeview.inflate
import com.abdhilabs.simplemultitypeview.entity.MenuItem

class MenuAdapter(private val data: List<Any>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private lateinit var addedListener: ((MenuItem, Int) -> Unit)
        private lateinit var removedListener: ((MenuItem, Int) -> Unit)

        private const val ITEM_HEADER = 0
        private const val ITEM_MENU = 1
    }

    fun setAddedItemListener(added: (MenuItem, Int) -> Unit) {
        addedListener = added
    }

    fun setRemovedItemListener(remove: (MenuItem, Int) -> Unit) {
        removedListener = remove
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM_HEADER -> MenuHeaderHolder(parent.inflate(R.layout.item_header))
            ITEM_MENU -> MenuItemHolder(parent.inflate(R.layout.item_menu))
            else -> throw throw IllegalArgumentException("Undefined view type")
        }
    }

    override fun getItemCount(): Int = data.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        //Supaya benar saat tampil di Recycler View
        when (holder.itemViewType) {
            ITEM_HEADER -> {
                val headerHolder = holder as MenuHeaderHolder
                headerHolder.itemHeader.text = data[position] as String
            }
            ITEM_MENU -> {
                val itemHolder = holder as MenuItemHolder
                itemHolder.bindContent(data[position] as MenuItem)
            }
            else -> throw IllegalArgumentException("Undefined view type")
        }

        //To make btnClick
        if (holder.itemViewType == ITEM_HEADER) {
            val headerHolder = holder as MenuHeaderHolder
            headerHolder.bindContent(data[position] as String)
        } else {
            val menuItem = data[position] as MenuItem

            val itemHolder = holder as MenuItemHolder

            itemHolder.btnAdd.setOnClickListener {
                addedListener(menuItem, position)
            }
            itemHolder.btnDelete.setOnClickListener {
                removedListener(menuItem, position)
            }

            if (menuItem.count == 0) {
                itemHolder.btnDelete.visibility = View.GONE
                itemHolder.itemCount.visibility = View.GONE
            } else {
                itemHolder.btnDelete.visibility = View.VISIBLE
                itemHolder.itemCount.visibility = View.VISIBLE
            }
        }
    }

    //Untuk mengecek instance dari tiap item berdasarkan posisinya
    override fun getItemViewType(position: Int): Int {
        return when (data[position]) {
            is String -> ITEM_HEADER
            is MenuItem -> ITEM_MENU
            else -> throw IllegalArgumentException("Undefined View Type")
        }
    }
}