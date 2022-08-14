package com.example.unsplashproject

import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProvider
import com.example.unsplashproject.model.login.UserModel
import com.example.unsplashproject.viewmodels.UserViewModel
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.regex.Matcher
import java.util.regex.Pattern

class LoginActivity : AppCompatActivity() {
    private lateinit var btnLogin: Button
    private var lvMain: ConstraintLayout? = null
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var textInputEmail: TextInputLayout
    private lateinit var textInputPassword: TextInputLayout
    private lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        bindView()
        isUserLogin()
        init()
    }

    private fun init() {
        userViewModel = ViewModelProvider(this)[UserViewModel::class.java]
        btnLogin.setOnClickListener {
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()
            userViewModel.isUserExist(email).observe(this)
            {
                if (it) {
                    userViewModel.getUser(email).observe(this)
                    { user ->
                        if (password == user?.password) {
                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                            saveInSharedPreferences(email)
                            finish()
                        } else {
                            textInputPassword.error="Error"
                            if (textInputPassword.childCount == 2) {
                                textInputPassword.getChildAt(1).visibility = View.GONE
                            }
                        }
                    }
                } else {
                    if (etEmail.text.toString().trim()
                            .isEmpty() || !(Patterns.EMAIL_ADDRESS.matcher(
                            etEmail.text.toString().trim()
                        )
                            .matches())
                    ) {
                        showSnackBarEmail()
                    } else if (etPassword.text.trim()
                            .isEmpty() || etPassword.text.trim().length < 8
                    ) {
                        showSnackBarPassword()
                    } else if (!isValidPassword(etPassword.text.toString().trim())) {
                        showSnackBarPassword()
                    } else {
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        insertDataToDatabase()
                        saveInSharedPreferences(email)
                        finish()
                    }
                }
            }

        }
    }

    private fun bindView() {
        btnLogin = findViewById(R.id.btn_login)
        lvMain = findViewById(R.id.sn_view)
        etEmail = findViewById(R.id.et_email)
        etPassword = findViewById(R.id.et_password)
        textInputEmail = findViewById(R.id.text_input_email)
        textInputPassword = findViewById(R.id.text_input_password)
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

    private fun insertDataToDatabase() {
        val email = etEmail.text.toString().trim()
        val password = etPassword.text.toString().trim()
        val user = UserModel(0, email, password, null, null, null)
        CoroutineScope(Dispatchers.IO).launch {
            userViewModel.insert(user)
        }
        Toast.makeText(this, "Successfully added", Toast.LENGTH_SHORT).show()
    }

    private fun saveInSharedPreferences(email: String) {
        val sharedPreferences: SharedPreferences = getSharedPreferences("UserEmail", MODE_PRIVATE)
        val edit = sharedPreferences.edit()
        edit.putString("email", email)
        edit.apply()
    }

    private fun isUserLogin(){
        val sharedPreferences: SharedPreferences = getSharedPreferences("UserEmail", MODE_PRIVATE)
        if (sharedPreferences.getString("email",null) != null){
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

}