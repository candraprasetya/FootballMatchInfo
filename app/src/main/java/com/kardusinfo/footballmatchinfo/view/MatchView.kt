package com.kardusinfo.footballmatchinfo.view

import com.kardusinfo.footballmatchinfo.model.MatchData

interface MatchView {

    fun showProgress()

    fun hideProgress()

    fun showDataMatchList(data: List<MatchData>)
}