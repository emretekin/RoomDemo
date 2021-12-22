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
import androidx.navigation.fragment.navArgs
import com.emretekin.roomdemo.R
import com.emretekin.roomdemo.model.User
import com.emretekin.roomdemo.viewModel.UserViewModel
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*


class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()

    private lateinit var userViewModel: UserViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        view.updateEtName.setText(args.currentUser.firstName)
        view.updateEtLastname.setText(args.currentUser.surname)
        view.updateEtAge.setText(args.currentUser.age.toString())

        view.btnUpdate.setOnClickListener {
            updateItem()
        }

        return view
    }

    private fun updateItem() {
        val firstName = updateEtName.text.toString()
        val lastName = updateEtLastname.text.toString()
        val age = updateEtAge.text.toString()

        if (isValid(firstName, lastName, age)) {
            val updatedUser = User(args.currentUser.id, firstName, lastName, age.toInt())
            userViewModel.updateUser(updatedUser)
            showToast("Data updated successfully")

            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
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