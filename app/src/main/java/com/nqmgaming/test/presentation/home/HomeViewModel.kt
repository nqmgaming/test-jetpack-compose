package com.nqmgaming.test.presentation.home

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nqmgaming.test.domain.model.Item
import com.nqmgaming.test.domain.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val appRepository: AppRepository
) : ViewModel() {

    var items = mutableStateOf<List<Item>>(emptyList())
        private set

    init {
        getAllItems()
    }

    fun getAllItems() {
        viewModelScope.launch {
            items.value = appRepository.getAllItems()
        }
    }

    fun deleteItem(item: Item) {
        Log.d("HomeViewModel", "deleteItem: $item")
        viewModelScope.launch {
            appRepository.deleteItem(item.ph31902Id ?: "")
            getAllItems()
        }
    }
}