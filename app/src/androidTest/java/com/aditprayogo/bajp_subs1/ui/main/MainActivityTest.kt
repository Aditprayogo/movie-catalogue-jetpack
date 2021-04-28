package com.aditprayogo.bajp_subs1.ui.main


import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.aditprayogo.bajp_subs1.R
import com.aditprayogo.bajp_subs1.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Created by Aditiya Prayogo.
 */
class MainActivityTest {

    @Rule
    @JvmField
    var activityRule = ActivityScenarioRule(MainActivity::class.java)


    @Before
    fun setUp() {
        ActivityScenario.launch(MainActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.espressoIdlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.espressoIdlingResource)
    }

    @Test
    fun loadMovies() {
        onView(withId(R.id.rvMovie)).check(matches(isDisplayed()))
        onView(withId(R.id.rvMovie)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                5
            )
        )
    }

    @Test
    fun loadDetailMovie() {
        onView(withId(R.id.rvMovie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.imgDetailMovie)).check(matches(isDisplayed()))
        onView(withId(R.id.nestedScroll)).perform(
            swipeUp()
        )
        onView(withId(R.id.txtOverview)).check(matches(isDisplayed()))
        onView(withId(R.id.txtDateOfRelease)).check(matches(isDisplayed()))
        onView(withId(R.id.rattingBar)).check(matches(isDisplayed()))
        onView(withId(R.id.txtStatus)).check(matches(isDisplayed()))
        onView(withId(R.id.chipGroup)).check(matches(isDisplayed()))
        onView(withId(R.id.chipGroup)).check(matches(isDisplayed()))
    }

    @Test
    fun loadTvShows() {
        onView(withId(R.id.viewPager)).perform(swipeLeft())
        onView(withId(R.id.rvTvShow)).check(matches(isDisplayed()))
        onView(withId(R.id.rvTvShow)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                5
            )
        )
    }

    @Test
    fun loadDetailTvShow() {
        onView(withId(R.id.viewPager)).perform(swipeLeft())
        onView(withId(R.id.rvTvShow)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.imgDetailMovie)).check(matches(isDisplayed()))
        onView(withId(R.id.nestedScroll)).perform(
            swipeUp()
        )
        onView(withId(R.id.txtOverview)).check(matches(isDisplayed()))
        onView(withId(R.id.txtDateOfRelease)).check(matches(isDisplayed()))
        onView(withId(R.id.rattingBar)).check(matches(isDisplayed()))
        onView(withId(R.id.txtStatus)).check(matches(isDisplayed()))
        onView(withId(R.id.chipGroup)).check(matches(isDisplayed()))
        onView(withId(R.id.chipGroup)).check(matches(isDisplayed()))
    }


}