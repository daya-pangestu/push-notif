package com.daya.taha.presentation.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.daya.taha.R
import com.daya.taha.util.launchFragmentInHiltContainer
import com.daya.taha.utils.EspressoIdlingResource
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@HiltAndroidTest
class HomeFragmentTest{
    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        hiltRule.inject()
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingresource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingresource)
    }

    @Test
    fun homeFragment_display_item() {
        val navController = TestNavHostController(
            ApplicationProvider.getApplicationContext())

        launchFragmentInHiltContainer<HomeFragment>(fragmentArgs = bundleOf("islecturer" to true)){
            navController.setGraph(R.navigation.nav_graph)
            Navigation.setViewNavController(this.requireView(), navController)
        }
        onView(withId(R.id.rv_main)).check(matches(isDisplayed()))

    }

    @Test
    fun homeFragment_item_toolbar_navigate_to_setting() {
        val navController = TestNavHostController(
            ApplicationProvider.getApplicationContext())

        launchFragmentInHiltContainer<HomeFragment> (fragmentArgs = bundleOf("islecturer" to true)){
            navController.setGraph(R.navigation.nav_graph)
            Navigation.setViewNavController(this.requireView(), navController)
        }

        onView(withId(R.id.settingsFragment))
            .perform(click())

        assertThat(navController.currentDestination?.id).isEqualTo(R.id.settingsFragment)
    }

    @Test
    fun homeFragment_item_toolbar_navigate_to_broadcastFragment() {
        val navController = TestNavHostController(
            ApplicationProvider.getApplicationContext())

        launchFragmentInHiltContainer<HomeFragment>(fragmentArgs = bundleOf("islecturer" to true)) {
            navController.setGraph(R.navigation.nav_graph)
            Navigation.setViewNavController(this.requireView(), navController)
        }

        onView(withId(R.id.broadCastFragment))
            .perform(click())

        assertThat(navController.currentDestination?.id).isEqualTo(R.id.broadCastFragment)
    }
}