package com.klvnd.mdintern.ui.thirdscreen

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.klvnd.mdintern.data.UserRepository
import com.klvnd.mdintern.data.api.ApiConfig
import com.klvnd.mdintern.data.response.DataItem
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {
    private val userRepository = UserRepository(ApiConfig.apiService)
    private val _users = MutableLiveData<List<DataItem>>()
    val users: LiveData<List<DataItem>> get() = _users

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        viewModelScope.launch {
            try {
                val userList = userRepository.getUsers(page = 1, perPage = 10)
                _users.value = userList
            } catch (e: Exception) {
                Toast.makeText(null, e.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun refreshUsers() {
        viewModelScope.launch {
            try {
                val userList = userRepository.getUsers(page = 1, perPage = 10)
                _users.value = userList
            } catch (e: Exception) {
                Toast.makeText(null, e.message, Toast.LENGTH_SHORT).show()
            }        }
    }
}
