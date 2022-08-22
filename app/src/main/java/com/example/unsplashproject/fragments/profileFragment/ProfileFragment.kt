package com.example.unsplashproject.fragments.profileFragment

import android.Manifest
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import com.example.unsplashproject.R
import com.example.unsplashproject.model.login.UserModel
import com.example.unsplashproject.viewmodels.UserViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import android.content.Intent
import android.content.pm.PackageManager
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.net.toUri
import java.util.regex.Matcher
import java.util.regex.Pattern

class ProfileFragment : Fragment() {

    private lateinit var firstName: LinearLayout
    private lateinit var lastName: LinearLayout
    private lateinit var email: LinearLayout
    private lateinit var password: LinearLayout

    private val userViewModel: UserViewModel by viewModels()

    private lateinit var profileFirstname: TextView
    private lateinit var profileLastname: TextView
    private lateinit var profileEmail: TextView
    private lateinit var profilePassword: TextView

    private lateinit var addProfilePhoto: ImageView
    private lateinit var profilePhoto: ImageView

    private val requestGalleryPermission = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            openGallery()
        } else {
            Toast.makeText(context, "Permission Denied", Toast.LENGTH_SHORT).show()
        }
    }
    private val requestImageLoad = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == AppCompatActivity.RESULT_OK && result.data != null) {
            val img = result.data?.data
            mUserModel.image = img.toString()
            userViewModel.updateUserImage(mUserModel)
            profilePhoto.setImageURI(img)

            val contentResolver = context?.contentResolver
            val takeFlags: Int = Intent.FLAG_GRANT_READ_URI_PERMISSION or
                    Intent.FLAG_GRANT_WRITE_URI_PERMISSION

            img?.let {
                contentResolver?.takePersistableUriPermission(it, takeFlags)
            }
        }
    }

    companion object {
        lateinit var mUserModel: UserModel
    }

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
        getDetailUser(getEmail.toString())
        profileEmail.text = getEmail
        firstName.setOnClickListener {
            val viewBottomSheet: View =
                layoutInflater.inflate(R.layout.first_name_bottom_sheet_design, null)
            val dialog = BottomSheetDialog(context!!, R.style.BottomSheetDialogTheme)
            dialog.setContentView(viewBottomSheet)
            dialog.show()
            val acceptButton: Button = viewBottomSheet.findViewById(R.id.btn_accept_first_name)
            val firstNameEditText: EditText = viewBottomSheet.findViewById(R.id.et_first_name)
            acceptButton.setOnClickListener {
                val firstName = firstNameEditText.text.toString().trim()
                if (firstName.isNotEmpty()) {
                    profileFirstname.text = firstName
                    mUserModel.firstName = firstName
                    userViewModel.updateFirstName(mUserModel)
                    Toast.makeText(context, "UserProfileUpdated", Toast.LENGTH_SHORT).show()
                    dialog.dismiss()
                } else {
                    Toast.makeText(context, "Field Should Not Empty", Toast.LENGTH_SHORT).show()
                }
            }
        }
        lastName.setOnClickListener {
            val viewBottomSheet: View =
                layoutInflater.inflate(R.layout.last_name_bottom_sheet_design, null)
            val dialog = BottomSheetDialog(context!!, R.style.BottomSheetDialogTheme)
            dialog.setContentView(viewBottomSheet)
            dialog.show()
            val acceptButton: Button = viewBottomSheet.findViewById(R.id.btn_accept_lastName)
            val lastNameEditText: EditText = viewBottomSheet.findViewById(R.id.et_last_name)
            acceptButton.setOnClickListener {
                val lastName = lastNameEditText.text.toString().trim()
                if (lastName.isNotEmpty()) {
                    profileLastname.text = lastName
                    mUserModel.lastName = lastName
                    userViewModel.updateLastName(mUserModel)
                    Toast.makeText(context, "UserProfileUpdated", Toast.LENGTH_SHORT).show()
                    dialog.dismiss()
                } else {
                    Toast.makeText(context, "Field Should Not Empty", Toast.LENGTH_SHORT).show()
                }
            }
        }
        email.setOnClickListener {
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
                        updateEmailShared(email)
                        profileEmail.text = email
                        mUserModel.email = email
                        userViewModel.updateEmail(mUserModel)
                        getDetailUser(email)
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
        password.setOnClickListener {
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
                            mUserModel.password = password
                            userViewModel.updatePassword(mUserModel)
                            Toast.makeText(context, "UserProfileUpdated", Toast.LENGTH_SHORT).show()
                            dialog.dismiss()
                        } else {
                            Toast.makeText(context, "fields Does Not Match", Toast.LENGTH_SHORT)
                                .show()
                        }
                    } else {
                        Toast.makeText(
                            context,
                            "AtLeast 8 Character And Contain 1..9 , a..z",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    Toast.makeText(context, "fields Should Not Empty", Toast.LENGTH_SHORT).show()
                }


            }
        }
        addProfilePhoto.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (requireActivity().checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                ) {
                    openGallery()

                } else {
                    requestGalleryPermission.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE)
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

    private fun getDetailUser(email: String) {
        userViewModel.getUser(email).observe(viewLifecycleOwner)
        {
            if (it != null) {
                mUserModel = it
                profilePhoto.setImageURI(it.image?.toUri())
                if (it.firstName != null) {
                    profileFirstname.text = it.firstName.toString().trim()

                } else {
                    profileFirstname.text = "FirstName"
                }
                if (it.lastName != null) {
                    profileLastname.text = it.lastName.toString().trim()

                } else {
                    profileLastname.text = "LastName"
                }
            }

        }
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        intent.type = "image/*"
        requestImageLoad.launch(intent)
    }

    private fun bindView(view: View) {
        firstName = view.findViewById(R.id.ll_first_name)
        lastName = view.findViewById(R.id.ll_last_name)
        email = view.findViewById(R.id.ll_email)
        password = view.findViewById(R.id.ll_password)

        profileFirstname = view.findViewById(R.id.profile_first_name)
        profileLastname = view.findViewById(R.id.profile_last_name)
        profileEmail = view.findViewById(R.id.profile_email)
        profilePassword = view.findViewById(R.id.profile_password)

        addProfilePhoto = view.findViewById(R.id.add_profile_photo)
        profilePhoto = view.findViewById(R.id.profile_iv)
    }

}

//                val live = userViewModel.getUser(getEmail!!)
//                val observer = Observer<UserModel?> {
//                    Log.d("itInfo", "$it")
//                    user = it!!
//                    user?.firstName = firstName
//                    Log.d("UserInfo", "$user")
//                    userViewModel.updateFirstName(user!!)
//                    live.removeObservers(viewLifecycleOwner)
//                }
//                live.observe(viewLifecycleOwner, observer)
