package com.example.kotlinankoaplikasifootbalclub.ui.main

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinankoaplikasifootbalclub.model.Event
import com.example.kotlinankoaplikasifootbalclub.util.DateTime
import kotlinx.android.extensions.LayoutContainer
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find

class MainAdapter(private val events: List<Event>, private val listener: (Event) -> Unit) : RecyclerView.Adapter<EventViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        return EventViewHolder(
            ItemUI().createView(AnkoContext.create(parent.context, parent))
        )
    }

    override fun getItemCount() = events.size

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        holder.bindItem(events[position], listener)
    }
}

class EventViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer{

    private val tvMathDate: TextView = containerView.find(ItemUI.tv_date)
    private val tvMathHomeTeam: TextView = containerView.find(ItemUI.tv_home_team)
    private val tvMathHomeScore: TextView = containerView.find(ItemUI.tv_home_score)
    private val tvMathAwayTeam: TextView = containerView.find(ItemUI.tv_away_team)
    private val tvMathAwayScope: TextView = containerView.find(ItemUI.tv_away_score)

    fun bindItem(event: Event, listener: (Event) -> Unit ){
        tvMathDate.text = DateTime.getLongDate(event.dateEvent)
        tvMathHomeTeam.text = event.strHomeTeam
        tvMathHomeScore.text = event.intHomeScore
        tvMathAwayTeam.text = event.strAwayTeam
        tvMathAwayScope.text = event.intAwayScore

        itemView.setOnClickListener { listener(event) }

    }
}