package com.kardusinfo.footballmatchinfo.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.kardusinfo.footballmatchinfo.R
import com.kardusinfo.footballmatchinfo.activity.fragment.NextMatchFragment
import com.kardusinfo.footballmatchinfo.model.MatchData
import org.jetbrains.anko.find

class NextMatchAdapter (
    private val dataItems: MutableList<MatchData>,
    private val listener: NextMatchFragment.OnFragmentInteractionListener?)
    : RecyclerView.Adapter<NextMatchAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NextMatchAdapter.ViewHolder {
//      TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.match_list, parent, false )
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        return dataItems.size
    }

//    override fun getItemCount(): Int = dataItems.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        holder.bindItem(dataItems[position], listener)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val mDateEvent  : TextView = view.find(R.id.tv_date_event)
        val mHomeTeam   : TextView = view.find(R.id.tv_home_team)
        val mAwayTeam   : TextView = view.find(R.id.tv_away_team)
        val mHomeScore  : TextView = view.find(R.id.tv_home_score)
        val mAwayScore  : TextView = view.find(R.id.tv_away_score)

        fun bindItem(item: MatchData, listener: NextMatchFragment.OnFragmentInteractionListener?) {
            mDateEvent.text = item.mDateEvent
            mHomeTeam.text = item.mHomeTeam
            mAwayTeam.text = item.mAwayTeam
            mHomeScore.text = item.mHomeScore
            mAwayScore.text = item.mAwayScore

            itemView.setOnClickListener {
                listener?.onFragmentInteraction(item)
            }
        }
    }

}
