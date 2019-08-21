package com.abdhilabs.simplemultitypeview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.abdhilabs.simplemultitypeview.adapter.MenuAdapter
import com.abdhilabs.simplemultitypeview.data.Menu
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var menuData = mutableListOf<Any>()
    private var totalOrder = 0
    private var totalPrice = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        menuData = Menu.menus.toMutableList()

        val menuAdapter = MenuAdapter(menuData)

        //Panggil Added Listener
        menuAdapter.setAddedItemListener { menuItem, position ->
            val itemCount = menuItem.count
            val menu = menuItem.copy()
            menu.count = itemCount + 1

            menuData[position] = menu
            menuAdapter.notifyItemChanged(position, menu)

            //Logic
            totalPrice += menu.price
            totalOrder++

            //Print Text
            tvReviewBooking.text = String.format(getString(R.string.review_booking), totalOrder, totalPrice)
        }

        menuAdapter.setRemovedItemListener { menuItem, position ->
            val itemCount = menuItem.count
            val menu = menuItem.copy()
            menu.count = itemCount - 1

            menuData[position] = menu
            menuAdapter.notifyItemChanged(position, menu)

            totalPrice -= menu.price
            totalOrder--

            if (totalOrder > 0) {
                tvReviewBooking.text = String.format(getString(R.string.review_booking), totalOrder, totalPrice)
            } else {
                tvReviewBooking.text = "-"
            }
        }

        menuItem.apply {
            hasFixedSize()
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = menuAdapter
        }
    }
}
