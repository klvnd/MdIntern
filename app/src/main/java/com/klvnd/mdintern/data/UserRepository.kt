package com.klvnd.mdintern.data

import com.klvnd.mdintern.data.api.ApiService
import com.klvnd.mdintern.data.response.DataItem

class UserRepository(private val apiService: ApiService) {

    suspend fun getUsers(page: Int, perPage: Int): List<DataItem> {
        val response = apiService.getUsers(page, perPage)
        return response.data?.map {
            DataItem(
                id = it?.id ?: 0,
                firstName = it?.firstName,
                lastName = it?.lastName,
                email = it?.email,
                avatar = it?.avatar
            )
        } ?: emptyList()
    }
}