package com.aditprayogo.bajp_subs1.data.repository.detail

import com.aditprayogo.bajp_subs1.data.remote.MovieServices
import javax.inject.Inject

/**
 * Created by Aditiya Prayogo.
 */
class DetailRepositoryImpl @Inject constructor(
    private val movieServices: MovieServices
) : DetailRepository {
}