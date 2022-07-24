package com.example.unsplashproject

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.snackbar.Snackbar
import java.util.regex.Matcher
import java.util.regex.Pattern

class MainActivity : AppCompatActivity() {
    private lateinit var btnLogin: Button
    private var lvMain: ConstraintLayout? = null
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        bindView()
        btnLogin.setOnClickListener {
            if (etEmail.text.toString().trim()
                    .isEmpty() || !(Patterns.EMAIL_ADDRESS.matcher(etEmail.text.toString().trim())
                    .matches())
            ) {
                showSnackBarEmail()
            } else if (etPassword.text.trim().isEmpty() || etPassword.text.trim().length < 8) {
                showSnackBarPassword()
            } else if (!isValidPassword(
                    etPassword.text.toString().trim()
                )
            ) {
                showSnackBarPassword()
            }

            val intent=Intent(this,)

        }
    }

    private fun bindView() {
        btnLogin = findViewById(R.id.btn_login)
        lvMain = findViewById(R.id.sn_view)
        etEmail = findViewById(R.id.et_email)
        etPassword = findViewById(R.id.et_password)
    }

    private fun isValidPassword(password: String): Boolean {
        val pattern: Pattern

        val passwordPattern = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}\$"
        pattern = Pattern.compile(passwordPattern)
        val matcher: Matcher = pattern.matcher(password)

        return matcher.matches()
    }

    private fun showSnackBarEmail() {
        val snackBar = Snackbar.make(lvMain!!, "", Snackbar.LENGTH_SHORT)
        val customSnackView = layoutInflater.inflate(R.layout.snackbar_email, null)
        snackBar.view.setBackgroundColor(Color.TRANSPARENT)
        val snackBarLayout: Snackbar.SnackbarLayout =
            snackBar.view as Snackbar.SnackbarLayout
        snackBarLayout.addView(customSnackView, 0)
        snackBar.show()
    }

    private fun showSnackBarPassword() {
        val snackBar = Snackbar.make(lvMain!!, "", Snackbar.LENGTH_SHORT)
        val customSnackView = layoutInflater.inflate(R.layout.snackbar_password, null)
        snackBar.view.setBackgroundColor(Color.TRANSPARENT)
        val snackBarLayout: Snackbar.SnackbarLayout =
            snackBar.view as Snackbar.SnackbarLayout
        snackBarLayout.addView(customSnackView, 0)
        snackBar.show()
    }

}