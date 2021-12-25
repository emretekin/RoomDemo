package com.emretekin.roomdemo.model

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "user_table")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val firstName: String,
    val surname: String,
    val age: Int,
    @Embedded
    val address: Address
): Parcelable

@Parcelize
data class Address(
    val streetName: String,
    val streetNumber: String,
    val city: String
): Parcelable

