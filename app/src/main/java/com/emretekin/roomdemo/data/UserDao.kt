package com.emretekin.roomdemo.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.emretekin.roomdemo.model.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)

    @Update
    suspend fun updateUser(user: User)

    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun readAllItems(): LiveData<List<User>>
}