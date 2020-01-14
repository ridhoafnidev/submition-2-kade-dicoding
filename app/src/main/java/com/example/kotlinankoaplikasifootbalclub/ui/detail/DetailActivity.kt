package com.example.kotlinankoaplikasifootbalclub.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.EventLog
import android.view.Gravity
import android.view.MenuItem
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.ScrollView
import com.example.kotlinankoaplikasifootbalclub.R
import com.example.kotlinankoaplikasifootbalclub.api.ApiRepository
import com.example.kotlinankoaplikasifootbalclub.model.Event
import com.example.kotlinankoaplikasifootbalclub.model.Team
import com.example.kotlinankoaplikasifootbalclub.util.*
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*

const val INTENT_DETAIL = "INTENT_DETAIL"

class DetailActivity : AppCompatActivity(), DetailView{
    private lateinit var data: Event
    private lateinit var progressBar: ProgressBar
    private lateinit var dataView: ScrollView
    private lateinit var presenter: DetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        data = intent.getParcelableExtra(INTENT_DETAIL)
        // print("kambing $data")
        setupLayout(data)
        setupEnv(data)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            android.R.id.home ->{
                finish()
                true
            }
            else ->super.onOptionsItemSelected(item)
        }
    }

    private fun setupEnv(data: Event?) {
        progressBar =find(R.id.progress_bar)
        val request = ApiRepository()
        val gson = Gson()
        presenter = DetailPresenter(this, request, gson)
        presenter.getTeamDetail(data?.idHomeTeam, data?.idAwayTeam)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Match Detail"
    }

    private fun setupLayout(data: Event) {
        relativeLayout {
            dataView = scrollView {
                linearLayout {
                    orientation = LinearLayout.VERTICAL
                    padding = dip(16)

                    // date
                    textCenter(DateTime.getLongDate(data.dateEvent))

                    // score
                    linearLayout {
                        gravity = Gravity.CENTER

                        textTitle(data.intHomeScore)
                        textTitle(" vs ")
                        textTitle(data.intAwayScore)
                    }

                    // team
                    linearLayout {
                        layoutTeamBadge(R.id.home_badge, data.strHomeTeam, data.strHomeFormation)
                            .lparams(matchParent, wrapContent, 1f)

                        layoutTeamBadge(R.id.away_badge, data.strAwayTeam, data.strAwayFormation)
                            .lparams(matchParent, wrapContent, 1f)
                    }

                    line()

                    layoutDetailItem("Goals", data.strHomeGoalDetails, data.strAwayGoalDetails)
                    layoutDetailItem("Shots", data.intHomeShots, data.intAwayShots)

                    line()

                    // lineups
                    textSubTitle("Lineups")

                    layoutDetailItem("Goal Keeper", data.strHomeLineupGoalkeeper, data.strAwayLineupGoalkeeper)
                    layoutDetailItem("Defense", data.strHomeLineupDefense, data.strAwayLineupDefense)
                    layoutDetailItem("Midfield", data.strHomeLineupMidfield, data.strAwayLineupMidfield)
                    layoutDetailItem("Forward", data.strHomeLineupForward, data.strAwayLineupForward)
                    layoutDetailItem("Substitutes", data.strHomeLineupSubstitutes, data.strAwayLineupSubstitutes)
                }
            }

            progressBar(R.id.progress_bar).lparams {
                centerInParent()
            }
        }
    }

    override fun showLoading() {
        progressBar.visible()
        dataView.invisible()
    }

    override fun hideLoading() {
        progressBar.invisible()
        dataView.visible()
    }

    override fun showTeamDetails(dataHomeTeam: List<Team>, dataAwayTeam: List<Team>) {
        val imgHomeBadge = find<ImageView>(R.id.home_badge)
        Picasso.get()
            .load(dataHomeTeam[0].strTeamBadge)
            .into(imgHomeBadge)

        val imgAwayBadge = find<ImageView>(R.id.away_badge)
        Picasso.get()
            .load(dataAwayTeam[0].strTeamBadge)
            .into(imgAwayBadge)
    }

}
