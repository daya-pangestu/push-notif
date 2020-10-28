package com.daya.shared.taha

import com.google.common.truth.Truth.assertThat
import org.junit.Test

import org.junit.Before

class SampelclazzTest {

    lateinit var sample :Sampelclazz

    @Before
    fun setUp() {
        sample = Sampelclazz()
    }

    @Test
    fun sum() {
        val hasil_sum = sample.sum(2,2,2,2)
        assertThat(hasil_sum).isEqualTo(8)

    }
}