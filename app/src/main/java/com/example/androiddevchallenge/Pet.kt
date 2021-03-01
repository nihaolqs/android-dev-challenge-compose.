package com.example.androiddevchallenge

import android.os.Parcelable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize
import java.util.UUID

@Parcelize
data class Pet(
    val name: String,
    val breed: String,
    val age: Int,
    val color: String,
    val id: String = UUID.randomUUID().toString()

) : Parcelable{
    @IgnoredOnParcel
    var isAdopt: MutableState<Boolean> = mutableStateOf(false)
}