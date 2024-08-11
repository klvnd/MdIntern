package com.klvnd.mdintern.ui.thirdscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.klvnd.mdintern.data.UserRepository
import com.klvnd.mdintern.data.response.DataItem
import com.klvnd.mdintern.data.api.ApiConfig
import kotlinx.coroutines.flow.Flow

class UserViewModel : ViewModel() {
    private val userRepository = UserRepository(ApiConfig.apiService)
    val users: Flow<PagingData<DataItem>> = Pager(
        config = PagingConfig(pageSize = 10),
        pagingSourceFactory = { userRepository.getUserPagingSource(10) }
    ).flow.cachedIn(viewModelScope)
}