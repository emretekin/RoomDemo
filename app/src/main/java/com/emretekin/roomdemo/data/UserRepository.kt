package com.emretekin.roomdemo.data

import androidx.lifecycle.LiveData

class UserRepository(private val userDao: UserDao) {

    val readAllData: LiveData<List<User>> = userDao.readAllItems()

    suspend fun addData(user: User) {
        userDao.addUser(user)
    }


}