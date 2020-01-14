package com.example.kotlinankoaplikasifootbalclub.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Event(
    val id: Long?,
    val idEvent: String?,
    val dateEvent: String?,

    // home team
    val idHomeTeam: String?,
    val strHomeTeam: String?,
    val intHomeScore: String?,
    val strHomeFormation: String?,
    val strHomeGoalDetails: String?,
    val intHomeShots: String?,
    val strHomeLineupGoalkeeper: String?,
    val strHomeLineupDefense: String?,
    val strHomeLineupMidfield: String?,
    val strHomeLineupForward: String?,
    val strHomeLineupSubstitutes: String?,

    // away team
    val idAwayTeam: String?,
    val strAwayTeam: String?,
    val intAwayScore: String?,
    val strAwayFormation: String?,
    val strAwayGoalDetails: String?,
    val intAwayShots: String?,
    val strAwayLineupGoalkeeper: String?,
    val strAwayLineupDefense: String?,
    val strAwayLineupMidfield: String?,
    val strAwayLineupForward: String?,
    val strAwayLineupSubstitutes: String?) : Parcelable