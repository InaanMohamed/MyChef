package com.mychef.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Order(
        var Title: String ,
        var Price: String ,
        var image: String
) : Parcelable

