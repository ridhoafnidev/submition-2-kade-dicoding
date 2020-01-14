package com.example.kotlinankoaplikasifootbalclub.model

import com.google.gson.annotations.SerializedName

data class Search( @SerializedName("idEvent")
                   var idEvent: String?=null,
                   @SerializedName("idHomeTeam")
                   var idHomeTeam: String?=null,
                   @SerializedName("strHomeTeam")
                   var strHomeTeam: String?=null,
                   @SerializedName("intHomeScore")
                   var intHomeScore: String?=null,
                   @SerializedName("strHomeFormation")
                   var strHomeFormation: String?=null,
                   @SerializedName("idAwayTeam")
                   var idAwayTeam: String?=null,
                   @SerializedName("strAwayTeam")
                   var strAwayTeam: String?=null,
                   @SerializedName("intAwayScore")
                   var intAwayScore: String?=null,
                   @SerializedName("dateEvent")
                   var dateEvent: String?=null)