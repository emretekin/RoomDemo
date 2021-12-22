package com.emretekin.roomdemo.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.emretekin.roomdemo.R
import com.emretekin.roomdemo.fragments.ListFragmentDirections
import com.emretekin.roomdemo.model.User
import kotlinx.android.synthetic.main.row_customer.view.*

class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var userList = emptyList<User>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_customer, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = userList[position]

        holder.itemView.txtNumber.text = item.id.toString()
        holder.itemView.txtFirstName.text = item.firstName
        holder.itemView.txtSurname.text = item.surname
        holder.itemView.txtAge.text = item.age.toString()

        holder.itemView.clRootLayout.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(item)
            holder.itemView.findNavController().navigate(action)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(user: List<User>) {
        this.userList = user
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return userList.size
    }
}