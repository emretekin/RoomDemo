package com.emretekin.roomdemo.data

import androidx.lifecycle.LiveData
import com.emretekin.roomdemo.model.User

class UserRepository(private val userDao: UserDao) {

    val readAllData: LiveData<List<User>> = userDao.readAllItems()

    suspend fun addData(user: User) {
        userDao.addUser(user)
    }

    suspend fun updateData(user: User) {
        userDao.updateUser(user)
    }

}