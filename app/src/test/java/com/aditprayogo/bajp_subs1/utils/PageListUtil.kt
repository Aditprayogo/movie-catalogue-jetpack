package com.aditprayogo.bajp_subs1.utils

import androidx.paging.PagedList
import org.mockito.Mockito.*

/**
 * Created by Aditiya Prayogo.
 */
object PageListUtil {

    fun <T> mockPagedList(list: List<T>) : PagedList<T> {
        val pagedList = mock(PagedList::class.java) as PagedList<T>

        `when`(pagedList[anyInt()]).then { invocation ->
            val index = invocation.arguments.first() as Int
            list[index]
        }
        `when`(pagedList.size).thenReturn(list.size)
        return pagedList
    }

}