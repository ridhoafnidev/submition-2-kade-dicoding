package com.example.kotlinankoaplikasifootbalclub.ui.detail

import com.example.kotlinankoaplikasifootbalclub.api.ApiRepository
import com.example.kotlinankoaplikasifootbalclub.api.TheSportDBApi
import com.example.kotlinankoaplikasifootbalclub.model.EventResponse
import com.example.kotlinankoaplikasifootbalclub.model.TeamResponse
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class DetailPresenter(private val view: DetailView,
                      private val apiRepository: ApiRepository,
                      private val gson: Gson)
{
fun getTeamDetail(idHomeTeam: String?, idAwayTeam: String?){
    view.showLoading()

    doAsync {
        val dataHomeTeam = gson.fromJson(apiRepository.doRequest(TheSportDBApi.getTeamDetails(idHomeTeam.toString())),
            TeamResponse::class.java
        )

        val dataAwayTeam = gson.fromJson(apiRepository.doRequest(TheSportDBApi.getTeamDetails(idAwayTeam.toString())),
                TeamResponse::class.java
        )

        uiThread {
            view.hideLoading()
            view.showTeamDetails(dataHomeTeam.teams!!, dataAwayTeam.teams!! )
        }

        }
    }
}