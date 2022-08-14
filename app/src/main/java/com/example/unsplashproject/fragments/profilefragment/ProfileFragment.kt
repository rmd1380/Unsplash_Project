package com.example.unsplashproject.fragments.profilefragment

import android.content.SharedPreferences
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.unsplashproject.R
import com.example.unsplashproject.model.login.UserModel
import com.example.unsplashproject.viewmodels.UserViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.util.regex.Matcher
import java.util.regex.Pattern

class ProfileFragment : Fragment() {

    private lateinit var firstNamell: LinearLayout
    private lateinit var lastNamell: LinearLayout
    private lateinit var emailll: LinearLayout
    private lateinit var passwordll: LinearLayout
    private val userViewModel: UserViewModel by viewModels()

    private lateinit var profileFirstname: TextView
    private lateinit var profileLastname: TextView
    private lateinit var profileEmail: TextView
    private lateinit var profilePassword: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return view(inflater, container)
    }

    private fun view(inflater: LayoutInflater, container: ViewGroup?): View {
        val view: View = inflater.inflate(R.layout.fragment_profile, container, false)
        init(view)
        return view
    }

    private fun init(view: View) {
        bindView(view)
        val getEmail = sharedPreferencesEMAIL()
        profileEmail.text = getEmail
        println("itttttt $getEmail")
        userViewModel.getUser(getEmail!!).observe(viewLifecycleOwner)
        {
            if (it?.firstName != null) {
                profileFirstname.text = it.firstName.toString().trim()

            } else {
                profileFirstname.text = "FirstName"
            }
            if (it?.lastName != null) {
                profileLastname.text = it.lastName.toString().trim()

            } else {
                profileLastname.text = "LastName"
            }

        }
        firstNamell.setOnClickListener {
            val viewBottomSheet: View =
                layoutInflater.inflate(R.layout.first_name_bottom_sheet_design, null)
            val dialog = BottomSheetDialog(context!!, R.style.BottomSheetDialogTheme)
            dialog.setContentView(viewBottomSheet)
            dialog.show()
            val acceptButton: Button = viewBottomSheet.findViewById(R.id.btn_accept_first_name)
            val firstNameEditText: EditText = viewBottomSheet.findViewById(R.id.et_first_name)
            acceptButton.setOnClickListener {
                val firstName = firstNameEditText.text.toString().trim()
                profileFirstname.text = firstName
                var user: UserModel?
                val live = userViewModel.getUser(getEmail)
                val observer = Observer<UserModel?> {
                    Log.d("itInfo", "$it")
                    user = it!!
                    user?.firstName = firstName
                    Log.d("UserInfo", "$user")
                    userViewModel.updateFirstName(user!!)
                    live.removeObservers(viewLifecycleOwner)
                }
                live.observe(viewLifecycleOwner, observer)
                Toast.makeText(context, "UserProfileUpdated", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            }
        }
        lastNamell.setOnClickListener {
            val viewBottomSheet: View =
                layoutInflater.inflate(R.layout.last_name_bottom_sheet_design, null)
            val dialog = BottomSheetDialog(context!!, R.style.BottomSheetDialogTheme)
            dialog.setContentView(viewBottomSheet)
            dialog.show()
            val acceptButton: Button = viewBottomSheet.findViewById(R.id.btn_accept_lastName)
            val lastNameEditText: EditText = viewBottomSheet.findViewById(R.id.et_last_name)
            acceptButton.setOnClickListener {
                val lastName = lastNameEditText.text.toString().trim()
                profileLastname.text = lastName
                var user: UserModel?
                val live = userViewModel.getUser(getEmail)
                val observer = Observer<UserModel?> {
                    Log.d("itInfo", "$it")
                    user = it!!
                    user?.lastName = lastName
                    Log.d("UserInfo", "$user")
                    userViewModel.updateLastName(user!!)
                    live.removeObservers(viewLifecycleOwner)
                }
                live.observe(viewLifecycleOwner, observer)

                Toast.makeText(context, "UserProfileUpdated", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            }
        }
        emailll.setOnClickListener {
            val viewBottomSheet: View =
                layoutInflater.inflate(R.layout.email_bottom_sheet_design, null)
            val dialog = BottomSheetDialog(context!!, R.style.BottomSheetDialogTheme)
            dialog.setContentView(viewBottomSheet)
            dialog.show()
            val acceptButton: Button = viewBottomSheet.findViewById(R.id.btn_accept_email)
            val emailEditText: EditText = viewBottomSheet.findViewById(R.id.et_email)
            acceptButton.setOnClickListener {
                val email = emailEditText.text.toString().trim()
                if (email.isNotEmpty()) {
                    if ((Patterns.EMAIL_ADDRESS.matcher(email).matches())) {
                        profileEmail.text = email
                        var user: UserModel?
                        val live = userViewModel.getUser(getEmail)
                        val observer = Observer<UserModel?> {
                            Log.d("itInfo", "$it")
                            user = it!!
                            user?.email = email
                            Log.d("UserInfo", "$user")
                            userViewModel.updateEmail(user!!)
                            updateEmailShared(email)
                            live.removeObservers(viewLifecycleOwner)
                        }
                        live.observe(viewLifecycleOwner, observer)

                        Toast.makeText(context, "UserProfileUpdated", Toast.LENGTH_SHORT).show()
                        dialog.dismiss()
                    } else {
                        Toast.makeText(context, "Email Is Not Correct", Toast.LENGTH_SHORT)
                            .show()
                    }

                } else {
                    Toast.makeText(context, "Email Is Empty", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
        passwordll.setOnClickListener {
            val viewBottomSheet: View =
                layoutInflater.inflate(R.layout.password_bottom_sheet_design, null)
            val dialog = BottomSheetDialog(context!!, R.style.BottomSheetDialogTheme)
            dialog.setContentView(viewBottomSheet)
            dialog.show()

            val acceptButton: Button = viewBottomSheet.findViewById(R.id.btn_accept_password)
            val passwordEditText: EditText = viewBottomSheet.findViewById(R.id.et_password)
            val confirmEditText: EditText = viewBottomSheet.findViewById(R.id.et_confirm)
            acceptButton.setOnClickListener {
                val password = passwordEditText.text.toString().trim()
                val confirm = confirmEditText.text.toString().trim()
                if (password.isNotEmpty() && confirm.isNotEmpty()) {
                    if (password.length >= 8 && isValidPassword(password)) {
                        if (password == confirm) {
                            var user: UserModel?
                            val live = userViewModel.getUser(getEmail)
                            val observer = Observer<UserModel?> {
                                Log.d("itInfo", "$it")
                                user = it!!
                                user?.password = password
                                Log.d("UserInfo", "$user")
                                userViewModel.updatePassword(user!!)
                                live.removeObservers(viewLifecycleOwner)
                            }
                            live.observe(viewLifecycleOwner, observer)

                            Toast.makeText(context, "UserProfileUpdated", Toast.LENGTH_SHORT).show()
                            dialog.dismiss()
                        } else {
                            Toast.makeText(context, "fields Does Not Match", Toast.LENGTH_SHORT).show()
                        }
                    } else
                    {
                        Toast.makeText(context, "AtLeast 8 Character And Contain 1..9 , a..z", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(context, "fields Should Not Empty", Toast.LENGTH_SHORT).show()
                }


            }
        }
    }

    private fun sharedPreferencesEMAIL(): String? {
        val sharedPreferences: SharedPreferences =
            activity!!.getSharedPreferences("UserEmail", AppCompatActivity.MODE_PRIVATE)
        return sharedPreferences.getString("email", null)
    }

    private fun updateEmailShared(email: String) {
        val sharedPreferences: SharedPreferences =
            activity!!.getSharedPreferences("UserEmail", AppCompatActivity.MODE_PRIVATE)
        val edit = sharedPreferences.edit()
        edit.putString("email", email)
        edit.apply()
    }

    private fun isValidPassword(password: String): Boolean {
        val pattern: Pattern
        val passwordPattern = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}\$"
        pattern = Pattern.compile(passwordPattern)
        val matcher: Matcher = pattern.matcher(password)
        return matcher.matches()
    }

    private fun bindView(view: View) {
        firstNamell = view.findViewById(R.id.ll_first_name)
        lastNamell = view.findViewById(R.id.ll_last_name)
        emailll = view.findViewById(R.id.ll_email)
        passwordll = view.findViewById(R.id.ll_password)

        profileFirstname = view.findViewById(R.id.profile_first_name)
        profileLastname = view.findViewById(R.id.profile_last_name)
        profileEmail = view.findViewById(R.id.profile_email)
        profilePassword = view.findViewById(R.id.profile_password)
    }

}