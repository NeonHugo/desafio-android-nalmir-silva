package com.nm.domain.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MCharacter(
    val id: Long,
    val name: String,
    val description: String,
    val thumbnail: String
) : Parcelable