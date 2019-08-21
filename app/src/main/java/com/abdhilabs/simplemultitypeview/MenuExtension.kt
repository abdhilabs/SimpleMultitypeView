package com.abdhilabs.simplemultitypeview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * To make Inflate
 */

fun ViewGroup.inflate(resource: Int) = LayoutInflater.from(context).inflate(resource, this, false) as View