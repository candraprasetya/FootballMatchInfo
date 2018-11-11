package com.kardusinfo.footballmatchinfo.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TeamData (val teamName: String, val teamImage: Int,
                         val teamDescription: String) : Parcelable