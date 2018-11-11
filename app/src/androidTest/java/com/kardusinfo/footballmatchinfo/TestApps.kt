package com.kardusinfo.footballmatchinfo

import android.support.test.espresso.Espresso
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.kardusinfo.footballmatchinfo.R.id.*
import com.kardusinfo.footballmatchinfo.activity.HomeActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TestApps {
    @Rule
    @JvmField var activityTestRule = ActivityTestRule(HomeActivity::class.java)

    @Test
    fun testRecyclerViewPrevMatchBehaviour() {
        Thread.sleep(3000)
        Espresso.onView(ViewMatchers.withId(rv_match_list)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(rv_match_list)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(7))
        Espresso.onView(ViewMatchers.withId(rv_match_list))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(5, ViewActions.click()))
    }

    @Test
    fun testRecyclerNextMatchBehaviour() {

        Espresso.onView(ViewMatchers.withId(btn_navigation)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(nav_next_match)).perform(ViewActions.click())

        Thread.sleep(3000)
        Espresso.onView(ViewMatchers.withId(rv_match_list)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(rv_match_list)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(7))
        Espresso.onView(ViewMatchers.withId(rv_match_list))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(5, ViewActions.click()))
    }

    @Test
    fun testFootballAppBehaviour(){

        // Melihat list prev match, scroll ke position 7, dan klik posisi 5
        Thread.sleep(3000)
        Espresso.onView(ViewMatchers.withId(rv_match_list)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(rv_match_list)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(7))
        Espresso.onView(ViewMatchers.withId(rv_match_list))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(5, ViewActions.click()))

        // Masuk kehalaman detail, klik botton favorite
        Thread.sleep(2000)
        Espresso.onView(ViewMatchers.withId(add_to_favorite)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(add_to_favorite)).perform(ViewActions.click())
        Thread.sleep(1500)
        Espresso.onView(ViewMatchers.withText("Added to Your Favorite Match"))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        //onView(withText("Remove From Favorite Match")).check(matches(isDisplayed()))

        // kembali ke homeActivity
        Espresso.pressBack()

        // Check button navigasi dan click ke fragment next match
        Espresso.onView(ViewMatchers.withId(btn_navigation)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(nav_next_match)).perform(ViewActions.click())

        // Menunggun 3 detik, Check list next match RecyclerView, scroll ke posisi 5, klik posisi 4
        Thread.sleep(3000)
        Espresso.onView(ViewMatchers.withId(rv_match_list)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(rv_match_list)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(5))
        Espresso.onView(ViewMatchers.withId(rv_match_list))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(4, ViewActions.click()))

        // Masuk ke halaman detail, klik Botton Favorite
        Thread.sleep(2000)
        Espresso.onView(ViewMatchers.withId(add_to_favorite)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(add_to_favorite)).perform(ViewActions.click())
        Thread.sleep(1500)
        Espresso.onView(ViewMatchers.withText("Added to Your Favorite Match"))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        // Kembali ke halaman HomeActivity
        Espresso.pressBack()

        // Ke Halaman Favorite
        Espresso.onView(ViewMatchers.withId(btn_navigation)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(nav_fav_match)).perform(ViewActions.click())

        // Masuk halaman Favorite tunggu 2 detik, check list Recyclerview, scroll ke posisi 2, click posisi 1
        Thread.sleep(2000)
        Espresso.onView(ViewMatchers.withId(rv_fav_match)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(rv_fav_match)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(1))
        Espresso.onView(ViewMatchers.withId(rv_fav_match))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(1, ViewActions.click()))

    }
}