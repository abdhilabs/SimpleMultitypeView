package com.abdhilabs.simplemultitypeview.adapter

import com.abdhilabs.simplemultitypeview.entity.MenuItem

interface MenuItemClickListener {

    fun onAdd(menuItem: MenuItem)
    fun onDelete(menuItem: MenuItem)

}