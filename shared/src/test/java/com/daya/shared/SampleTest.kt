package com.daya.shared

import com.daya.shared.taha.SampleClass
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class SampleTest {

    @Test
    fun `sample return sum return total number `() {
        val sample = SampleClass()
        val hasil = sample.sum(2,2)
        assertThat(hasil).isEqualTo(4)
    }
}