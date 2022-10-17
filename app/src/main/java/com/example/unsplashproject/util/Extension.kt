package com.example.unsplashproject.util

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.example.unsplashproject.R
import com.google.android.material.snackbar.Snackbar

@SuppressLint("InflateParams")
fun View.snackBar(snackTitle: String, snackBody:String) {
    val snack = Snackbar.make(this, "", Snackbar.LENGTH_SHORT)
    val customSnackView = LayoutInflater.from(context).inflate(R.layout.snackbar_layout,null)
    snack.view.setBackgroundColor(Color.TRANSPARENT)
    val snackErrorTitle=customSnackView.findViewById<TextView>(R.id.snack_error_title)
    snackErrorTitle.text=snackTitle
    val snackBodyTitle=customSnackView.findViewById<TextView>(R.id.snack_error_body)
    snackBodyTitle.text=snackBody
    val snackBarLayout: Snackbar.SnackbarLayout = snack.view as Snackbar.SnackbarLayout
    snackBarLayout.addView(customSnackView, 0)
    snack.show()

//    val snackBar = Snackbar.make(lvMain!!, "", Snackbar.LENGTH_SHORT)
//    val customSnackView = layoutInflater.inflate(R.layout.snackbar_password, null)
//    snackBar.view.setBackgroundColor(Color.TRANSPARENT)
//    val snackBarLayout: Snackbar.SnackbarLayout =
//        snackBar.view as Snackbar.SnackbarLayout
//    snackBarLayout.addView(customSnackView, 0)
//    snackBar.show()
}