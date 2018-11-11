package com.kardusinfo.footballmatchinfo.presenter

import com.google.gson.Gson
import com.kardusinfo.footballmatchinfo.api.ApiRepository
import com.kardusinfo.footballmatchinfo.api.TheSportDbApi
import com.kardusinfo.footballmatchinfo.model.BadgeResponse
import com.kardusinfo.footballmatchinfo.model.MatchDataDetailResponse
import com.kardusinfo.footballmatchinfo.utils.CoroutineContextProvider
import com.kardusinfo.footballmatchinfo.view.MatchDetailView
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MatchDetailPresenter (public val detailMatchEventView: MatchDetailView,
                            public val apiRequest: ApiRepository,
                            public val gson: Gson
                            , private val context: CoroutineContextProvider = CoroutineContextProvider()
) {

    fun getTeamBadge( team: String?, teamType: String? ){

        doAsync {
            val dataTeam = gson.fromJson(apiRequest
                .doRequest(TheSportDbApi.getBadge(team))
                , BadgeResponse::class.java )

            uiThread {
                if(teamType == "Away")
                    detailMatchEventView.showAwayTeamBadge(dataTeam.teams)
                else
                    detailMatchEventView.showHomeTeamBadge(dataTeam.teams)

            }
        }
    }


    fun getMatchEventDetail(event: String?){

        async(context.main) {
            val dataDetail = bg {
                gson.fromJson(apiRequest.doRequest(
                    TheSportDbApi.getDetailMatch(event))
                    , MatchDataDetailResponse::class.java)
            }
            detailMatchEventView.showDetailMatch(dataDetail.await().events)
        }
    }

}