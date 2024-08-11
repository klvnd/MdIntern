package com.klvnd.mdintern.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.klvnd.mdintern.data.api.ApiService
import com.klvnd.mdintern.data.response.DataItem

class UserPagingSource(
    private val apiService: ApiService,
    private val pageSize: Int
) : PagingSource<Int, DataItem>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DataItem> {
        val position = params.key ?: 1
        return try {
            val response = apiService.getUsers(page = position, perPage = pageSize)
            val users = response.data?.map {
                DataItem(
                    id = it?.id ?: 0,
                    firstName = it?.firstName,
                    lastName = it?.lastName,
                    email = it?.email,
                    avatar = it?.avatar
                )
            } ?: emptyList()

            LoadResult.Page(
                data = users,
                prevKey = if (position == 1) null else position - 1,
                nextKey = if (users.isEmpty()) null else position + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, DataItem>): Int? {
        return null
    }
}