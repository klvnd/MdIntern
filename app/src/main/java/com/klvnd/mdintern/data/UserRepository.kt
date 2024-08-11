package com.klvnd.mdintern.data

import com.klvnd.mdintern.data.api.ApiService

class UserRepository(private val apiService: ApiService) {

    fun getUserPagingSource(pageSize: Int) = UserPagingSource(apiService, pageSize)
}
