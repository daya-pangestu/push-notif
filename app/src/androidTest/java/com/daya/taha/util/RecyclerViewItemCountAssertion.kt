package com.daya.taha.util

import android.view.View
import androidx.recyclerview.widget.RecyclerView

import androidx.test.espresso.NoMatchingViewException

import androidx.test.espresso.ViewAssertion
import com.google.common.truth.Truth.assertThat

class RecyclerViewItemCountAssertion(private val expectedCount: Int = 0,private val isAtLeast :Int = 0) : ViewAssertion {
    override fun check(view: View, noViewFoundException: NoMatchingViewException?) {
        if (noViewFoundException != null) throw noViewFoundException
        if (expectedCount == 0 && isAtLeast == 0) throw Exception("either expectedCount or isAtleast have more than 0 value")
        val recyclerView = view as RecyclerView
        val adapter = recyclerView.adapter
        if (isAtLeast > 0) {
            assertThat(adapter!!.itemCount).isAtLeast(isAtLeast)
            return
        }
        assertThat(adapter!!.itemCount).isEqualTo(expectedCount)
    }
}