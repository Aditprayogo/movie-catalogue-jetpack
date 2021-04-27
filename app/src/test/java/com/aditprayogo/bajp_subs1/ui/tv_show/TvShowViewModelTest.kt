package com.aditprayogo.bajp_subs1.ui.tv_show

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.aditprayogo.bajp_subs1.core.state.ResultState
import com.aditprayogo.bajp_subs1.data.remote.responses.TvShowResponses
import com.aditprayogo.bajp_subs1.domain.tv_show.TvShowUseCase
import com.aditprayogo.bajp_subs1.utils.DataDummyTemp
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.atLeastOnce
import com.nhaarman.mockitokotlin2.clearInvocations
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.*
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.*
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

/**
 * Created by Aditiya Prayogo.
 */
@ObsoleteCoroutinesApi
@ExperimentalCoroutinesApi
class TvShowViewModelTest {

    private lateinit var tvShowViewModel: TvShowViewModel
    private val tvShowUseCase = mock(TvShowUseCase::class.java)

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = TestCoroutineDispatcher()

    @Mock
    lateinit var listTvShowResponses: Observer<List<TvShowResponses>>

    @Captor
    lateinit var resultCaptor: ArgumentCaptor<List<TvShowResponses>>

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        MockitoAnnotations.initMocks(this)

        tvShowViewModel = TvShowViewModel(tvShowUseCase)
        tvShowViewModel.resultTvShowFromApi.observeForever(listTvShowResponses)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `get discover tv shows and return success`() = runBlockingTest {

        val result = ResultState.Success(DataDummyTemp.discoverTvShowResponses)

        `when`(tvShowUseCase.getDiscoverTvShows())
            .thenReturn(result)

        tvShowViewModel.getTvShowResultsFromApi()

        verify(listTvShowResponses, atLeastOnce()).onChanged(resultCaptor.capture())

        assertThat(result.data.tvShowResponses).isEqualTo(resultCaptor.allValues.first())

        clearInvocations(tvShowUseCase, listTvShowResponses)

    }

}