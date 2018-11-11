package com.kardusinfo.footballmatchinfo.model

import com.google.gson.annotations.SerializedName

data class MatchData (
    @SerializedName("idEvent")
    var mIdEvent: String? = null,

    @SerializedName("dateEvent")
    var mDateEvent: String? = null,

    // -- HOME TEAM --
    @SerializedName("strHomeTeam")
    var mHomeTeam: String? = null,

    @SerializedName("intHomeScore")
    var mHomeScore: String? = null,

    @SerializedName("strHomeGoalDetails") // X
    val mHomeGoalDetails: String? = null,

    // -- AWAY TEAM --
    @SerializedName("strAwayTeam")
    var mAwayTeam: String? = null,

    @SerializedName("intAwayScore")
    var mAwayScore: String? = null,

    @SerializedName("strAwayGoalDetails") // X
    val mAwayGoalDetails: String? = null
)