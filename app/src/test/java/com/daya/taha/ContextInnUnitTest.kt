package com.daya.taha

import android.os.Build
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(maxSdk = Build.VERSION_CODES.P, minSdk = Build.VERSION_CODES.P)
class ContextInnUnitTest {

    @Test
    fun `app not null in unit testing`() {
        val app = ApplicationProvider.getApplicationContext<TahaApplication>()

        assertThat(app).isNotNull()
    }
}
