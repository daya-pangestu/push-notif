package com.daya.shared

import com.daya.SampleClass
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class ExampleUnitTest {
    val sample = SampleClass()

    @Test
    fun addition_isCorrect() {
        val hasil = sample.sum(2,2)
        assertThat(hasil).isEqualTo(4)
    }
}