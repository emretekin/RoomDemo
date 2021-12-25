package com.emretekin.roomdemo.fragments

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.emretekin.roomdemo.R
import com.emretekin.roomdemo.model.Address
import com.emretekin.roomdemo.model.User
import com.emretekin.roomdemo.viewModel.UserViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*

class AddFragment : Fragment() {

    private var userViewModel: UserViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_add, container, false)

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        view.btnAdd.setOnClickListener {
            insertDataToDatabase()
        }

        return view
    }

    private fun insertDataToDatabase() {
        val firstName = etName.text.toString()
        val lastName = etLastname.text.toString()
        val age = etAge.text.toString()

        if (isValid(firstName, lastName, age)) {
            val address = Address("Özgün Sk.", "1255", "İstanbul") //Dummy data
            val user = User(0 , firstName, lastName, age.toInt(), address)
            userViewModel?.addUser(user)
            showToast("Data added successfully")
            findNavController().popBackStack()
        } else {
            showToast("Please fill out all fields")
        }
    }

    private fun isValid(firstName: String, lastName: String, age:String): Boolean {
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && TextUtils.isEmpty(age))
    }

    fun Context?.toast(text: CharSequence, duration: Int = Toast.LENGTH_LONG) = this?.let { Toast.makeText(it, text, duration).show() }
    fun Fragment?.showToast(text: CharSequence, duration: Int = Toast.LENGTH_LONG) = this?.let { requireActivity().toast(text, duration) }
}