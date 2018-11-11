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
import com.kardusinfo.footballmatchinfo.adapter.PrevMatchAdapter
import com.kardusinfo.footballmatchinfo.api.ApiRepository
import com.kardusinfo.footballmatchinfo.model.MatchData
import com.kardusinfo.footballmatchinfo.presenter.MatchPresenter
import com.kardusinfo.footballmatchinfo.view.MatchView
import kotlinx.android.synthetic.main.fragment_prev_match.view.*
import org.jetbrains.anko.support.v4.onRefresh

class PrevMatchFragment : Fragment(), MatchView {

    private var dataItems: MutableList<MatchData> = mutableListOf()
    private var listener : OnFragmentInteractionListener? = null

    private lateinit var matchEventPresenter : MatchPresenter
    private lateinit var adapter             : PrevMatchAdapter
    private lateinit var swipeRefreshLayout  : SwipeRefreshLayout
    private lateinit var progressBar         : ProgressBar

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_prev_match, container, false)

        val rv = view.findViewById<RecyclerView>(R.id.rv_match_list)
        rv.layoutManager = LinearLayoutManager(context)
        adapter = PrevMatchAdapter(dataItems, listener)
        rv.adapter = adapter

        swipeRefreshLayout  = view.swipe_refresh
        progressBar         = view.progress_bar

        swipeRefreshLayout.onRefresh {
            matchEventPresenter.getMatchPrevData("4335")
        }

        showProgress()

        val apiReq  = ApiRepository()
        val gson    = Gson()
        matchEventPresenter = MatchPresenter(this, apiReq, gson )
        matchEventPresenter.getMatchPrevData("4335")

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
        // TODO: Update argument type and name
        fun onFragmentInteraction(item: MatchData)
    }

    companion object {
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() = PrevMatchFragment()
    }

    override fun showProgress() {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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
