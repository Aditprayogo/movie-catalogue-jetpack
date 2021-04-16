package com.aditprayogo.bajp_subs1.ui.main


import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeUp
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import com.aditprayogo.bajp_subs1.R
import com.aditprayogo.bajp_subs1.utils.MovieDummy
import org.junit.Before
import org.junit.Test

/**
 * Created by Aditiya Prayogo.
 */
class MainActivityTest {

    private val dummyMovies = MovieDummy.getMovies()
    private val dummyTvShows = MovieDummy.getTvShow()

    @Before
    fun setUp() {
        ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    fun loadMovies() {
        onView(withId(R.id.rvMovie)).check(matches(isDisplayed()))
        onView(withId(R.id.rvMovie)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyMovies.size
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
        onView(withId(R.id.txtOverview)).check(matches(withText(dummyMovies[0].overview)))

        onView(withId(R.id.txtTitleMovie)).check(matches(isDisplayed()))
        onView(withId(R.id.txtTitleMovie)).check(matches(withText(dummyMovies[0].title)))

        onView(withId(R.id.txtGenre)).check(matches(isDisplayed()))
        onView(withId(R.id.txtGenre)).check(matches(withText(dummyMovies[0].genre)))

        onView(withId(R.id.txtDateOfRelease)).check(matches(isDisplayed()))
        onView(withId(R.id.txtDateOfRelease)).check(matches(withText(dummyMovies[0].dateOfRealese)))

        onView(withId(R.id.txtDuration)).check(matches(isDisplayed()))
        onView(withId(R.id.txtDuration)).check(matches(withText(dummyMovies[0].duration)))
    }

    @Test
    fun loadTvShows() {
        onView(withText("TvShow")).perform(click())
        onView(withId(R.id.rvTvShow)).check(matches(isDisplayed()))
        onView(withId(R.id.rvTvShow)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyTvShows.size
            )
        )
    }

    @Test
    fun loadDetailTvShow() {
        onView(withText("TvShow")).perform(click())
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
        onView(withId(R.id.txtOverview)).check(matches(withText(dummyTvShows[0].overview)))

        onView(withId(R.id.txtTitleMovie)).check(matches(isDisplayed()))
        onView(withId(R.id.txtTitleMovie)).check(matches(withText(dummyTvShows[0].title)))

        onView(withId(R.id.txtGenre)).check(matches(isDisplayed()))
        onView(withId(R.id.txtGenre)).check(matches(withText(dummyTvShows[0].genre)))

        onView(withId(R.id.txtDateOfRelease)).check(matches(isDisplayed()))
        onView(withId(R.id.txtDateOfRelease)).check(matches(withText(dummyTvShows[0].dateOfRealese)))

        onView(withId(R.id.txtDuration)).check(matches(isDisplayed()))
        onView(withId(R.id.txtDuration)).check(matches(withText(dummyTvShows[0].duration)))
    }

}