package com.daya.taha.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.rules.TestWatcher
import org.junit.runner.Description


class MainCoroutineRule @OptIn(ExperimentalCoroutinesApi::class) constructor(
    val testDispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher()
) : TestWatcher() {

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun starting(description: Description?) {
        super.starting(description)
        Dispatchers.setMain(testDispatcher)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun finished(description: Description?) {
        super.finished(description)
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }
}

@OptIn(ExperimentalCoroutinesApi::class)
fun MainCoroutineRule.runBlockingTest(block: suspend TestCoroutineScope.() -> Unit) {
    return this.testDispatcher.runBlockingTest {
        block()
    }
}

/**
 * Creates a new [CoroutineScope] with the rule's testDispatcher
 */
fun MainCoroutineRule.CoroutineScope(): CoroutineScope = CoroutineScope(testDispatcher)