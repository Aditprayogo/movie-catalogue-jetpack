package com.aditprayogo.bajp_subs1.utils

import android.content.Context
import android.view.View
import android.widget.Toast

/**
 * Created by Aditiya Prayogo.
 */

fun View.setVisible() {
    visibility = View.VISIBLE
}

fun View.setGone() {
    visibility = View.GONE
}

fun Context.toast(message : CharSequence) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}
