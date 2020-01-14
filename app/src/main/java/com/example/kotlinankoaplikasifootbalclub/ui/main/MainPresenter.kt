package com.example.kotlinankoaplikasifootbalclub.ui.main

import com.example.kotlinankoaplikasifootbalclub.api.ApiRepository
import com.example.kotlinankoaplikasifootbalclub.api.TheSportDBApi
import com.example.kotlinankoaplikasifootbalclub.model.EventResponse
import com.example.kotlinankoaplikasifootbalclub.model.LeagueResponse
import com.example.kotlinankoaplikasifootbalclub.model.SearchResponse
import com.example.kotlinankoaplikasifootbalclub.model.TeamResponse
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.lang.NullPointerException

class MainPresenter (private val view: MainView,
                     private val apiRepository: ApiRepository,
                     private val gson: Gson){

    var menu = 1

    /*
    fun getTeamList(league: String?){
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository.
                doRequest(TheSportDBApi.getTeams(league)),
                TeamResponse::class.java
            )
            uiThread {
                view.hideLoading()
                view.showTeamList(data.teams)
            }
        }
    }
    */

    fun getLeagueAll(){
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository.
                doRequest(TheSportDBApi.getLeagueAll()),
                LeagueResponse::class.java
            )
            uiThread {
                view.hideLoading()
                view.showLeagueList(data)
            }
        }
    }

    fun getEventsPrev(id: String?){
    menu = 1
    view.showLoading()

    doAsync {
        val data = gson.fromJson(apiRepository.
        doRequest(TheSportDBApi.getLeaguePrev(id)),
            EventResponse::class.java
        )
        uiThread {
            view.hideLoading()
            try {
                view.showEventList(data.events!!)
            }catch (e: NullPointerException){
                view.showEmptyData()
            }
        }
    }
}

    fun getEventsNext(id: String?) {
        menu = 2
        view.showLoading()

        doAsync {
            val data = gson.fromJson(apiRepository.
                doRequest(TheSportDBApi.getLeagueNext(id)),
                EventResponse::class.java
            )
            uiThread {
                view.hideLoading()
                try {
                    view.showEventList(data.events!!)
                }catch (e: NullPointerException){
                    view.showEmptyData()
                }
            }
        }
    }

    fun searchEvents(query: String) {
        menu = 1
        view.showLoading()

        doAsync {
            val data = gson.fromJson(apiRepository.
                doRequest(TheSportDBApi.searchLeague(query)),
                SearchResponse::class.java
            )
            uiThread {
                view.hideLoading()
                try {
                    view.showEventList(data.event!!)
                }catch (e: NullPointerException){
                    view.showEmptyData()
                }
            }
        }
    }

}