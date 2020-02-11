package com.nm.domain.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Comic(
    val id: Long,
    val title: String,
    val description: String,
    val price: Double,
    val thumbnail: String
) : Parcelable