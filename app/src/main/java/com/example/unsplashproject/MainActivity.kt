package com.example.unsplashproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var btnLogin:TextView
    private var lvView:ConstraintLayout? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnLogin=findViewById(R.id.btn_login)
        lvView=findViewById(R.id.snack_bar_view)
        val layout: View =layoutInflater.inflate(R.layout.snackbar,lvView)
        btnLogin.setOnClickListener{
            Toast(this).apply {
                view=layout
                duration=Toast.LENGTH_SHORT
                setGravity(Gravity.BOTTOM,10,10)
            }.show()

        }
    }
}