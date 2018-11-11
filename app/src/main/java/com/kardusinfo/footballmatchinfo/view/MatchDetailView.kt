package com.kardusinfo.footballmatchinfo.view

import android.widget.ProgressBar
import com.kardusinfo.footballmatchinfo.model.BadgeDataItem
import com.kardusinfo.footballmatchinfo.model.MatchDataDetail

interface MatchDetailView {

    fun showProgress(progressBar: ProgressBar)

    fun hideProgress(progressBar: ProgressBar)

    fun showDetailMatch (data : List<MatchDataDetail>)

    fun showHomeTeamBadge (data: List<BadgeDataItem>)

    fun showAwayTeamBadge (data: List<BadgeDataItem>)

}