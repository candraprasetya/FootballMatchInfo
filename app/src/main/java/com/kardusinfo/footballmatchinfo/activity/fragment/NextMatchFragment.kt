package com.kardusinfo.footballmatchinfo.activity.fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.google.gson.Gson
import com.kardusinfo.footballmatchinfo.R
import com.kardusinfo.footballmatchinfo.adapter.NextMatchAdapter
import com.kardusinfo.footballmatchinfo.api.ApiRepository
import com.kardusinfo.footballmatchinfo.model.MatchData
import com.kardusinfo.footballmatchinfo.presenter.MatchPresenter
import com.kardusinfo.footballmatchinfo.view.MatchView
import kotlinx.android.synthetic.main.fragment_next_match.view.*
import org.jetbrains.anko.support.v4.onRefresh

class NextMatchFragment : Fragment(), MatchView {

    private var dataItems: MutableList<MatchData> = mutableListOf()
    private var listener : OnFragmentInteractionListener? = null

    private lateinit var matchEventPresenter : MatchPresenter
    private lateinit var adapter             : NextMatchAdapter
    private lateinit var swipeRefreshLayout  : SwipeRefreshLayout
    private lateinit var progressBar         : ProgressBar


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_next_match, container, false)

        val rv = view.findViewById<RecyclerView>(R.id.rv_match_list)
        rv.layoutManager = LinearLayoutManager(context)
        adapter = NextMatchAdapter(dataItems, listener)
        rv.adapter = adapter

        swipeRefreshLayout  = view.swipe_refresh
        progressBar         = view.progress_bar

        swipeRefreshLayout.onRefresh {
            matchEventPresenter.getMatchNextData("4335")
        }
        showProgress()

        val api     = ApiRepository()
        val gson    = Gson()
        matchEventPresenter = MatchPresenter(this, api, gson )

        matchEventPresenter.getMatchNextData("4335")

        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnFragmentInteractionListener {

        fun onFragmentInteraction(item: MatchData)
    }

    companion object {
        @JvmStatic
        fun newInstance() = NextMatchFragment()
    }

    override fun showProgress() {

        progressBar.visibility = View.VISIBLE

    }

    override fun hideProgress() {

        progressBar.visibility = View.GONE

    }

    override fun showDataMatchList(data: List<MatchData>) {

        swipeRefreshLayout.isRefreshing = false
        dataItems.clear()
        dataItems.addAll(data)
        adapter.notifyDataSetChanged()
        hideProgress()

    }

}
