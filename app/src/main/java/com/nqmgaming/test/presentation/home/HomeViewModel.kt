package com.nqmgaming.test.presentation.home

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

    private fun getAllItems() {
        viewModelScope.launch {
            items.value = appRepository.getAllItems()
        }
    }

}