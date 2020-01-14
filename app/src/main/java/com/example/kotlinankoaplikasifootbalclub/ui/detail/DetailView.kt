package com.example.kotlinankoaplikasifootbalclub.ui.detail

import com.example.kotlinankoaplikasifootbalclub.model.Event
import com.example.kotlinankoaplikasifootbalclub.model.Team

interface DetailView{
    fun showLoading()
    fun hideLoading()
    fun showTeamDetails(dataHomeTeam: List<Team>, dataAwayTeam: List<Team>)
}