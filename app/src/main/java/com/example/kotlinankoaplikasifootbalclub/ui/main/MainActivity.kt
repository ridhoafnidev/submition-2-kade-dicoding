package com.example.kotlinankoaplikasifootbalclub.ui.main

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.Menu
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.kotlinankoaplikasifootbalclub.R
import com.example.kotlinankoaplikasifootbalclub.api.ApiRepository
import com.example.kotlinankoaplikasifootbalclub.model.Event
import com.example.kotlinankoaplikasifootbalclub.model.League
import com.example.kotlinankoaplikasifootbalclub.model.LeagueResponse
import com.example.kotlinankoaplikasifootbalclub.model.Team
import com.example.kotlinankoaplikasifootbalclub.ui.detail.DetailActivity
import com.example.kotlinankoaplikasifootbalclub.util.invisible
import com.example.kotlinankoaplikasifootbalclub.util.visible
import com.google.gson.Gson
import org.jetbrains.anko.*
import org.jetbrains.anko.design.bottomNavigationView
import org.jetbrains.anko.recyclerview.v7.recyclerView

const val INTENT_DETAIL = "INTENT_DETAIL"
class MainActivity : AppCompatActivity(),
    MainView, AnkoLogger {

    private var teams: MutableList<Team> = mutableListOf()
    private lateinit var presenter: MainPresenter
    private lateinit var adapter: MainAdapter
    private lateinit var leagueName: String
    private lateinit var league: League
    private var events: MutableList<Event> = mutableListOf()

    private lateinit var rvListLeague: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var searchView: SearchView
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var spinner: Spinner
    private lateinit var spinnerLayout: LinearLayout
    private lateinit var emptyDataView: LinearLayout

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupLayout()
        setupEnv()

        /*
        swipeRefresh.onRefresh {
           // presenter.getTeamList(leagueName)
        }

         */
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)

        val searchItem = menu?.findItem(R.id.action_search)
        searchView = searchItem?.actionView as SearchView
        searchView.setQueryHint("Search View Hint")
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String): Boolean {

                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                // task HERE
                //applicationContext?.startActivity<SearchEventActivity>("query" to query)
                Log.v("test", query)
                presenter.searchEvents(query)
                return false
            }
        })
        return true
    }

    private fun setupEnv() {

        val request = ApiRepository()
        val gson = Gson()
        presenter =
            MainPresenter(
                this,
                request,
                gson
            )
            presenter.getLeagueAll()

        adapter = MainAdapter(events) {
            startActivity<DetailActivity>(INTENT_DETAIL to it)
        }
        presenter.getLeagueAll()
        rvListLeague.adapter = adapter
    }

    private fun setupLayout() {
        linearLayout {
            orientation = LinearLayout.VERTICAL

            spinnerLayout = linearLayout {
                orientation = LinearLayout.VERTICAL
                backgroundColor = Color.LTGRAY

                spinner = spinner {
                    id = R.id.spinner
                    padding = dip(16)
                    minimumHeight = dip(80)
                }
            }

            relativeLayout {
                emptyDataView = linearLayout {
                    orientation = LinearLayout.VERTICAL

                    imageView {
                        setImageResource(R.drawable.ic_no_data)
                    }

                    textView {
                        gravity = Gravity.CENTER
                        padding = dip(8)
                        text = "Tidak Ada Data"
                    }
                }.lparams {
                    centerInParent()
                }

                rvListLeague = recyclerView {
                    id = R.id.recycler_view
                    layoutManager = LinearLayoutManager(context)
                    addItemDecoration(DividerItemDecoration(context, 1 ))
                }.lparams(matchParent, matchParent) {
                    topOf(R.id.botton_navigation_view)
                }

                progressBar = progressBar{
                }.lparams{
                    centerInParent()
                }

                bottomNavigationView {
                    id = R.id.botton_navigation_view
                    backgroundColor = Color.WHITE

                    menu.apply {
                        add(0, R.id.bnv_match_prev, 0, "Prev. Match")
                            .setIcon(R.drawable.ic_event_black_24dp)
                            .setOnMenuItemClickListener {
                                presenter.getEventsPrev(league.idLeague!!)
                                false
                            }

                        add(0, R.id.bnv_match_next, 0, "Next Match")
                            .setIcon(R.drawable.ic_apps_black_24dp)
                            .setOnMenuItemClickListener {
                                presenter.getEventsNext(league.idLeague!!)
                                false
                            }
                    }
                }.lparams(matchParent, wrapContent) {
                    alignParentBottom()
                }
            }
        }
    }
/*
    private fun setupLayout() {
        linearLayout{
            lparams(width = matchParent, height = wrapContent)
            orientation = LinearLayout.VERTICAL
            backgroundColor = Color.GRAY

            swipeRefresh = swipeRefreshLayout {
                setColorSchemeResources(R.color.colorAccent,
                    android.R.color.holo_green_light,
                    android.R.color.holo_orange_light,
                    android.R.color.holo_red_light)

            spinnerLayout =linearLayout{
                orientation =LinearLayout.VERTICAL
                spinner = spinner{
                    id =R.id.spinner
                    padding =dip(16)
                    minimumHeight = dip(80)
                }
            }

                relativeLayout {

                    emptyDataView = linearLayout {
                        orientation = LinearLayout.VERTICAL

                        imageView {
                            setImageResource(R.drawable.ic_no_data)
                        }

                        textView {
                            gravity = Gravity.CENTER
                            padding = dip(8)
                            text = "Data Tidak Ditemukan!"
                        }
                    }.lparams{
                        centerInParent()
                    }

                    rvListLeague = recyclerView {
                        id = R.id.recycler_view
                        layoutManager = LinearLayoutManager(context)
                    }.lparams(matchParent, matchParent){
                        topOf(R.id.botton_navigation_view)
                    }

                    progressBar = progressBar{
                    }.lparams{
                        centerInParent()
                    }

                    bottomNavigationView{
                        id = R.id.botton_navigation_view
                        backgroundColor =Color.WHITE

                        menu.apply {
                            add(0, R.id.bnv_match_prev, 0, "Prev. Match")
                                .setIcon(R.drawable.ic_apps_black_24dp)
                                .setOnMenuItemClickListener {
                                    presenter.getEventsPrev(league.idLeague!!)
                                    false
                                }

                            add(0, R.id.bnv_match_next, 0, "Next Match")
                                .setIcon(R.drawable.ic_event_black_24dp)
                                .setOnMenuItemClickListener {
                                    presenter.getEventsNext(league.idLeague!!)
                                    false
                                }
                        }
                    }.lparams(matchParent, wrapContent) {
                        alignParentBottom()
                    }
                }
            }
        }
    }
*/
    override fun showLoading() {
        progressBar.visible()
        rvListLeague.invisible()
        emptyDataView.invisible()
    }

    override fun hideLoading() {
        progressBar.invisible()
        rvListLeague.visible()
        emptyDataView.invisible()
    }

    override fun showEmptyData() {
        progressBar.invisible()
        rvListLeague.invisible()
        emptyDataView.visible()
    }

    /*
    override fun showTeamList(data: List<Team>) {
        swipeRefresh.isRefreshing = false
        teams.clear()
        teams.addAll(data)
        adapter.notifyDataSetChanged()
    }
     */

    override fun showLeagueList(data: LeagueResponse) {

        /*
        val spinnerItems = data.leagues
        val spinnerAdapter = ArrayAdapter(applicationContext, R.layout.spinner_style, spinnerItems)
        spinner.adapter = spinnerAdapter
        */

        // simple

        spinner.adapter = ArrayAdapter(applicationContext, android.R.layout.simple_spinner_dropdown_item, data.leagues)

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {

                 league = spinner.selectedItem as League
                toast(league.idLeague)
                when(presenter.menu){
                    1 ->presenter.getEventsPrev(league.idLeague)
                    2 ->presenter.getEventsNext(league.idLeague)
                }

            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    override fun showEventList(data: List<Event>) {
        showEventListData(data)
    }

    private fun showEventListData(data: List<Event>) {
        events.clear()
        events.addAll(data)
        adapter.notifyDataSetChanged()
        rvListLeague.scrollToPosition(0)
    }
}
