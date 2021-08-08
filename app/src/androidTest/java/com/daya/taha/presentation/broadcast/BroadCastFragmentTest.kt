package com.daya.taha.presentation.broadcast

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.daya.taha.R
import com.daya.taha.util.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class BroadCastFragmentTest{
    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun broadcastFragment_isEnabled() {
        val navController = TestNavHostController(
            ApplicationProvider.getApplicationContext())

        launchFragmentInHiltContainer<BroadCastFragment> {
            navController.setGraph(R.navigation.nav_graph)
            Navigation.setViewNavController(requireView(), navController)
        }

        onView(withId(R.id.ed_title))
            .perform(typeText("title"), closeSoftKeyboard())
        onView(withId(R.id.ed_desc))
            .perform(typeText("description"), closeSoftKeyboard())
        onView(withId(R.id.ed_url_access))
            .perform(typeText("google.com"), closeSoftKeyboard())

        onView(withId(R.id.btn_browse_img))
            .check(matches(isEnabled()))
    }

}