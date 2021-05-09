package com.aditprayogo.bajp_subs1.ui.favorite_tv_show

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.aditprayogo.bajp_subs1.data.local.database.entity.TvShowEntity
import com.aditprayogo.bajp_subs1.domain.tv_show.TvShowUseCase
import com.google.common.truth.Truth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations

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

    @Mock
    lateinit var pagedList: PagedList<TvShowEntity>

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        MockitoAnnotations.initMocks(this)

        favoriteTvShowViewModel = FavoriteTvShowViewModel(tvShowUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `get tv show favorite from db and should return success`()  {
        val dummyTvShow = pagedList
        Mockito.`when`(dummyTvShow.size).thenReturn(5)

        val tvShows = MutableLiveData<PagedList<TvShowEntity>>()
        tvShows.value = dummyTvShow

        Mockito.`when`(tvShowUseCase.getTvShowFavorite()).thenReturn(tvShows)
        val tvShow = favoriteTvShowViewModel.getTvShowFavorite().value

        Mockito.verify(tvShowUseCase).getTvShowFavorite()

        Truth.assertThat(tvShow).isNotNull()
        Truth.assertThat(tvShow?.size).isEqualTo(5)

        favoriteTvShowViewModel.getTvShowFavorite().observeForever(tvShowList)
        Mockito.verify(tvShowList).onChanged(dummyTvShow)
    }

}