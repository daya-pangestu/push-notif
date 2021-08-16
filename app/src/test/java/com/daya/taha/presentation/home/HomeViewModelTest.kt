package com.daya.taha.presentation.home


import com.daya.shared.taha.data.Resource
import com.daya.shared.taha.domain.usecase.InfoPagingUseCase
import com.daya.shared.taha.domain.usecase.LogOutUseCase
import com.daya.taha.utils.MainCoroutineRule
import com.daya.taha.utils.runBlockingTest
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class HomeViewModelTest {

    @get:Rule
    var coroutineRule = MainCoroutineRule()

    private val logOutUseCase: LogOutUseCase = mock()
    private val infoPagingUseCase: InfoPagingUseCase = mock()
    lateinit var homeViewModel: HomeViewModel

    private val dummyResSucces = Resource.success(Unit)

    @Before
    fun setUp() {
        homeViewModel = HomeViewModel(logOutUseCase,infoPagingUseCase)
    }

//    @Test
//    fun logOut() = coroutineRule.runBlockingTest {
//        whenever(logOutUseCase(Unit)).thenReturn(dummyResSucces)
//        val result = homeViewModel.logOut()
//        assertThat(result).isEqualTo(dummyResSucces)
//    }

}