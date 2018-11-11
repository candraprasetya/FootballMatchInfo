package com.kardusinfo.footballmatchinfo.presenter

import com.google.gson.Gson
import com.kardusinfo.footballmatchinfo.api.ApiRepository
import com.kardusinfo.footballmatchinfo.api.TheSportDbApi
import com.kardusinfo.footballmatchinfo.model.MatchDataResponse
import com.kardusinfo.footballmatchinfo.utils.CoroutineContextProvider
import com.kardusinfo.footballmatchinfo.view.MatchView
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class MatchPresenter (private val matchEventView: MatchView,
                           private val apiRequest: ApiRepository,
                           private val gson: Gson
                           ,private val context: CoroutineContextProvider = CoroutineContextProvider()
) {

    fun getMatchPrevData(league: String?){
        async(context.main) {
            val dataMatch = bg {
                gson.fromJson(apiRequest
                    .doRequest(TheSportDbApi.getPrevMatch(league))
                    , MatchDataResponse::class.java
                )
            }

            matchEventView.showDataMatchList(dataMatch.await().events)
        }
    }


    fun getMatchNextData(league: String?) {
        async(context.main) {
            val dataMatch = bg {
                gson.fromJson( apiRequest.doRequest(TheSportDbApi.getNextMatch(league))
                    , MatchDataResponse::class.java )
            }

            matchEventView.showDataMatchList(dataMatch.await().events)
        }
    }
}
