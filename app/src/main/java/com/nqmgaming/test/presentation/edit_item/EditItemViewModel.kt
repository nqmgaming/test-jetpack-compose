package com.nqmgaming.test.presentation.edit_item

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.nqmgaming.test.domain.model.Item
import com.nqmgaming.test.domain.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditItemViewModel @Inject constructor(
    private val appRepository: AppRepository,
    application: Application,
    savedStateHandle: SavedStateHandle
): AndroidViewModel(application) {

    var itemId = savedStateHandle.get<String>("itemId")
        private set

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

    val itemEdited = MutableStateFlow<Boolean>(false)

    fun onPriceChange(price: String) {
        this.price.update { price }
    }

    fun onModelChange(model: String) {
        this.model.update { model }
    }

    fun onStatusChange(status: Boolean) {
        this.status.update { status }
    }

    init {
        savedStateHandle.get<String>("itemId")?.let { id ->
            if (id.isNotEmpty()){
                getItemById(id)
            }else{
                toast("Item not found")
            }
        }
    }

    private fun getItemById(id: String){
        viewModelScope.launch {
            val item = appRepository.getItemById(id)
            itemName.value = item.ph31902Name ?: ""
            price.value = item.ph31902Price?.toString() ?: ""
            model.value = item.ph31902Model ?: ""
            status.value = item.ph31902Status ?: false
        }
    }

    fun updateItem(){
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

        viewModelScope.launch {
            val item = appRepository.updateItem(
                Item(
                    ph31902Id = itemId,
                    ph31902Name = name,
                    ph31902Price = price.toDouble(),
                    ph31902Model = model,
                    ph31902Status = status
                )
            )
            itemEdited.update { true }
            canNavigateUp.update { true }
        }
    }

    private fun toast(message: String){
        Toast.makeText(getApplication(), message, Toast.LENGTH_SHORT).show()
    }
}