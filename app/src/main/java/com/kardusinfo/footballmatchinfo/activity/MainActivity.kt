package com.kardusinfo.footballmatchinfo.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.kardusinfo.footballmatchinfo.R
import com.kardusinfo.footballmatchinfo.R.array.*
import com.kardusinfo.footballmatchinfo.adapter.TeamAdapter
import com.kardusinfo.footballmatchinfo.model.TeamData
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.indeterminateProgressDialog
import org.jetbrains.anko.startActivity


class MainActivity : AppCompatActivity() {


    private var teamDataItems: MutableList<TeamData> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val progressDialog = indeterminateProgressDialog("Getting Data! Please wait...")

        progressDialog.show()

        initData()

        progressDialog.dismiss()

        team_list_rv.layoutManager = LinearLayoutManager(this)
        team_list_rv.adapter = TeamAdapter(this, teamDataItems) { itemClicked(it)}
    }

    private fun itemClicked(itemData: TeamData) {
        startActivity<TeamDetails>(TeamDetails.TEAM_NAME to itemData.teamName,
            TeamDetails.TEAM_IMAGE to itemData.teamImage,
            TeamDetails.TEAM_DESCRIPTION to itemData.teamDescription )
    }

    private fun initData() {
        val teamName    = resources.getStringArray(club_name)
        val teamImage   = resources.obtainTypedArray(club_image)
        val teamDesc    = resources.getStringArray(club_description)

        teamDataItems.clear()

        for (i in teamName.indices) {
            teamDataItems.add(TeamData(teamName[i],
                teamImage.getResourceId(i,0), teamDesc[i]))
        }

        teamImage.recycle()
    }
}


