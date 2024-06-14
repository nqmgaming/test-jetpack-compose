package com.nqmgaming.test.presentation.detail

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.nqmgaming.test.domain.model.Item
import com.nqmgaming.test.domain.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val appRepository: AppRepository,
    application: Application,
    saveStateHandle: SavedStateHandle
) : AndroidViewModel(application) {


    var item = MutableSharedFlow<Item>()
        private set

    init {
        saveStateHandle.get<Int>("itemId")?.let { id ->
            getItemById(id)
        }
    }

    private fun getItemById(id: Int) {
        viewModelScope.launch {
            item.emit(appRepository.getItemById(id))
        }
    }
}