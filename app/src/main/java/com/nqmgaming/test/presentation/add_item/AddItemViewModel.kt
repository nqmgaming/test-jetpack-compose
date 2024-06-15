package com.nqmgaming.test.presentation.add_item

import android.app.Application
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nqmgaming.test.domain.model.Item
import com.nqmgaming.test.domain.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddItemViewModel @Inject constructor(
    private val appRepository: AppRepository,
    application: Application
) : AndroidViewModel(application) {
    var itemName = MutableStateFlow<String>(value = "")
        private set

    var price = MutableStateFlow<String>(value = "")
        private set

    var model = MutableStateFlow<String>(value = "")
        private set

    var status = MutableStateFlow<Boolean>(value = false)
        private set

    var canNavigateUp = MutableStateFlow<Boolean>(value = false)
        private set

    fun onItemNameChange(itemName: String) {
        this.itemName.update { itemName }
    }

    val itemAdded = MutableStateFlow<Boolean>(false)

    fun onPriceChange(price: String) {
        this.price.update { price }
    }

    fun onModelChange(model: String) {
        this.model.update { model }
    }

    fun onStatusChange(status: Boolean) {
        this.status.update { status }
    }

    fun insertItem() {
        val name = itemName.value
        val price = price.value
        val model = model.value
        val status = status.value

        if (name.isEmpty()) {
            toast("Name is required")
            return
        }
        if (price.isEmpty()) {
            toast("Price is required")
            return
        }

        if (price.toDoubleOrNull() == null) {
            toast("Price must be a number")
            return
        }

        if (model.isEmpty()) {
            toast("Model is required")
            return
        }

        val item = Item(
            ph31902Name = name,
            ph31902Price = price.toDouble(),
            ph31902Model = model,
            ph31902Status = status
        )
        viewModelScope.launch {
            appRepository.insertItem(item)
            itemAdded.update { true }
        }.invokeOnCompletion {
            resetFields()
            toast(
                if (it == null) {
                    "Item added successfully"
                } else {
                    "Error adding item"
                }
            )
            canNavigateUp.update { true }
        }
    }

    private fun resetFields() {
        itemName.value = ""
        price.value = ""
        model.value = ""
        status.value = false
    }

    private fun toast(message: String) {
        Toast.makeText(getApplication(), message, Toast.LENGTH_SHORT).show()
    }
}