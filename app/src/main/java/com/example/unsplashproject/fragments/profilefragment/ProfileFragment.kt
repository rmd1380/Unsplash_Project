package com.example.unsplashproject.fragments.profilefragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import com.example.unsplashproject.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.qualifiers.ApplicationContext

class ProfileFragment : Fragment() {

    private lateinit var firstName: LinearLayout
    private lateinit var lastName: LinearLayout
    private lateinit var email: LinearLayout
    private lateinit var password: LinearLayout

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

        firstName.setOnClickListener{
            val viewBottomSheet:View =
                layoutInflater.inflate(R.layout.first_name_bottom_sheet_design,null)
            val dialog = BottomSheetDialog(context!!,R.style.BottomSheetDialogTheme)
            dialog.setContentView(viewBottomSheet)
            dialog.show()
        }
        lastName.setOnClickListener {
            val viewBottomSheet: View =
                layoutInflater.inflate(R.layout.last_name_bottom_sheet_design, null)
            val dialog = BottomSheetDialog(context!!,R.style.BottomSheetDialogTheme)
            dialog.setContentView(viewBottomSheet)
            dialog.show()
        }
        email.setOnClickListener {
            val viewBottomSheet: View =
                layoutInflater.inflate(R.layout.email_bottom_sheet_design, null)
            val dialog = BottomSheetDialog(context!!,R.style.BottomSheetDialogTheme)
            dialog.setContentView(viewBottomSheet)
            dialog.show()
        }
        password.setOnClickListener {
            val viewBottomSheet: View =
                layoutInflater.inflate(R.layout.password_bottom_sheet_design, null)
            val dialog = BottomSheetDialog(context!!,R.style.BottomSheetDialogTheme)
            dialog.setContentView(viewBottomSheet)
            dialog.show()
        }
    }

    private fun bindView(view: View) {
        firstName = view.findViewById(R.id.ll_first_name)
        lastName = view.findViewById(R.id.ll_last_name)
        email = view.findViewById(R.id.ll_email)
        password = view.findViewById(R.id.ll_password)
    }

}