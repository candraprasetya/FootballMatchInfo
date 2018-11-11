package com.kardusinfo.footballmatchinfo.activity.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kardusinfo.footballmatchinfo.R
import com.kardusinfo.footballmatchinfo.activity.DetailActivity
import com.kardusinfo.footballmatchinfo.adapter.FavMatchAdapter
import com.kardusinfo.footballmatchinfo.database.FavMatch
import com.kardusinfo.footballmatchinfo.database.database
import kotlinx.android.synthetic.main.fragment_fav_match.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.onRefresh

class FavMatchFragment : Fragment() {

    private var FavMatch : MutableList<FavMatch> = mutableListOf()
    private lateinit var adapter: FavMatchAdapter
    private lateinit var listMatch : RecyclerView
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adapter = FavMatchAdapter(FavMatch){
            ctx.startActivity<DetailActivity>("id_event" to "${it.idEvent}",
                "home_team" to "${it.homeTeam}",
                "home_score" to "${it.homeScore}",
                "home_score" to "${it.homeScore}",
                "away_team" to "${it.awayTeam}",
                "away_score" to "${it.awayScore}",
                "away_score" to "${it.awayScore}",
                "date_event" to "${it.dateEvent}")
        }

//        listMatch.layoutManager = LinearLayoutManager(ctx);

        listMatch = rv_fav_match
        listMatch.layoutManager = LinearLayoutManager(ctx)

        listMatch.adapter = adapter
        showFavMatch()

        fav_swipe_refresh.onRefresh {
            FavMatch.clear()
            showFavMatch()
        }

    }

    private fun showFavMatch() {
        context?.database?.use {
            fav_swipe_refresh.isRefreshing = false
            val result = select(com.kardusinfo.footballmatchinfo.database.FavMatch.TABLE_FAV_MATCH)
            val favorite = result.parseList(classParser<FavMatch>())
            FavMatch.addAll(favorite)
            adapter.notifyDataSetChanged()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fav_match, container, false)
    }
}
