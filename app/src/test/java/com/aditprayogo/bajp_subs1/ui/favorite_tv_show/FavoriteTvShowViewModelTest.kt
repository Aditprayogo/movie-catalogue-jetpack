package com.aditprayogo.bajp_subs1.ui.favorite_tv_show

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.aditprayogo.bajp_subs1.core.state.ResultState
import com.aditprayogo.bajp_subs1.data.local.database.entity.TvShowEntity
import com.aditprayogo.bajp_subs1.domain.tv_show.TvShowUseCase
import com.aditprayogo.bajp_subs1.utils.DataDummyTemp
import com.google.common.truth.Truth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.*
import org.mockito.Mockito.mock

/**
 * Created by Aditiya Prayogo.
 */
@ExperimentalCoroutinesApi
@ObsoleteCoroutinesApi
class FavoriteTvShowViewModelTest {

    private lateinit var favoriteTvShowViewModel: FavoriteTvShowViewModel
    private val tvShowUseCase = mock(TvShowUseCase::class.java)

    @get:Rule
    val instantTaskExecutor = InstantTaskExecutorRule()

    private val testDispatcher = TestCoroutineDispatcher()

    @Mock
    lateinit var tvShowList : Observer<List<TvShowEntity>>

    @Captor
    lateinit var tvShowListArgumentCaptor : ArgumentCaptor<List<TvShowEntity>>

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        MockitoAnnotations.initMocks(this)

        favoriteTvShowViewModel = FavoriteTvShowViewModel(tvShowUseCase)
        favoriteTvShowViewModel.resultTvShowFromDb.observeForever(tvShowList)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `get tv show favorite from db and should return success`() = runBlockingTest {
        val tvShowData = DataDummyTemp.listFavoriteTvShow
        val result = ResultState.Success(tvShowData)

        Mockito.`when`(tvShowUseCase.getTvShowFavorite()).thenReturn(result)

        favoriteTvShowViewModel.getTvShowFavorite()

        Mockito.verify(tvShowList, Mockito.atLeastOnce()).onChanged(tvShowListArgumentCaptor.capture())
        Truth.assertThat(result.data).isEqualTo(tvShowListArgumentCaptor.allValues.first())
        Mockito.clearInvocations(tvShowUseCase, tvShowList)
    }

}