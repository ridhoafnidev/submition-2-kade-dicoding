package com.example.kotlinankoaplikasifootbalclub.ui.main

import com.example.kotlinankoaplikasifootbalclub.model.Event
import com.example.kotlinankoaplikasifootbalclub.model.LeagueResponse
import com.example.kotlinankoaplikasifootbalclub.model.Team

interface MainView {
    fun showLoading()
    fun hideLoading()
    fun showEmptyData()
    fun showLeagueList(data: LeagueResponse)
    fun showEventList(data: List<Event>)
}