package com.daya.taha.presentation.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.closeSoftKeyboard
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.daya.shared.taha.domain.model.News
import com.daya.taha.R
import com.daya.taha.presentation.broadcast.BroadCastFragment
import com.daya.taha.util.launchFragmentInHiltContainer
import com.daya.taha.utils.EspressoIdlingResource
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class DetailFragmentTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val dummyNews =  News(
        senderId = "1",
        title = "title",
        description = "description",
        urlAccess = "google.com",
        urlImage = "",
    )

    @Before
    fun setup() {
        hiltRule.inject()
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingresource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingresource)
    }

    @Test
    fun detailFragment_show_expected_view() {
        val navController = TestNavHostController(
            ApplicationProvider.getApplicationContext())

        launchFragmentInHiltContainer<DetailFragment>(
            fragmentArgs = bundleOf("news_arg" to dummyNews)
        ) {
            navController.setGraph(R.navigation.nav_graph)
            Navigation.setViewNavController(requireView(), navController)
        }

        onView(withId(R.id.tv_url_access)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_desc)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_share_link)).check(matches(isDisplayed()))

    }
}