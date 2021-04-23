package com.aditprayogo.bajp_subs1.core.state

/**
 * Created by Aditiya Prayogo.
 */
sealed class LoaderState {
    object ShowLoading : LoaderState()
    object HideLoading : LoaderState()
}