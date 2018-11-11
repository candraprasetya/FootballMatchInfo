package com.kardusinfo.footballmatchinfo.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.kardusinfo.footballmatchinfo.R
import com.kardusinfo.footballmatchinfo.database.FavMatch
import org.jetbrains.anko.find
import org.jetbrains.anko.sdk25.coroutines.onClick

class FavMatchAdapter( private val favoriteMatch : List<FavMatch>,
                            private val listener: (FavMatch) -> Unit)
    : RecyclerView.Adapter<FavMatchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavMatchViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.match_list, parent, false)
        return FavMatchViewHolder(view)
    }

    override fun getItemCount(): Int = favoriteMatch.size


    override fun onBindViewHolder(holder: FavMatchViewHolder, position: Int) {
        holder.bindItem(favoriteMatch[position], listener)
    }

}

class FavMatchViewHolder (view: View) : RecyclerView.ViewHolder(view) {

    val mDateEvent  : TextView = view.find(R.id.tv_date_event)
    val mHomeTeam   : TextView = view.find(R.id.tv_home_team)
    val mAwayTeam   : TextView = view.find(R.id.tv_away_team)
    val mHomeScore  : TextView = view.find(R.id.tv_home_score)
    val mAwayScore  : TextView = view.find(R.id.tv_away_score)

    fun bindItem(itemFav: FavMatch, listener: (FavMatch) -> Unit ) {
        mDateEvent.text = itemFav.dateEvent
        mHomeTeam.text = itemFav.homeTeam
        mAwayTeam.text = itemFav.awayTeam
        mHomeScore.text = itemFav.homeScore
        mAwayScore.text = itemFav.awayScore

        itemView.onClick { listener(itemFav) }
    }
}


