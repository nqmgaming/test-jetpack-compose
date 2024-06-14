package com.nqmgaming.test.presentation.add_item

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.nqmgaming.test.core.components.TextFieldComponent
import com.nqmgaming.test.presentation.home.HomeViewModel
import com.nqmgaming.test.presentation.utils.Screen

@Composable
fun AddItemScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: AddItemViewModel = hiltViewModel(),
    homeViewModel: HomeViewModel = hiltViewModel(),
) {

    val name by viewModel.itemName.collectAsState()
    val price by viewModel.price.collectAsState()
    val description by viewModel.description.collectAsState()
    val status by viewModel.status.collectAsState()

    val canNavigateUp by viewModel.canNavigateUp.collectAsState()
    val itemAdded by viewModel.itemAdded.collectAsState()

    LaunchedEffect(key1 = itemAdded) {
        if (itemAdded) {
            homeViewModel.getAllItems()
        }
    }
    LaunchedEffect(key1 = canNavigateUp) {
        if (canNavigateUp) {
            navController.navigate(Screen.Home.route) {
                popUpTo(Screen.Home.route) {
                    inclusive = true
                }
            }
        }
    }

    AddItem(
        modifier = modifier,
        name = name,
        onChangeName = viewModel::onItemNameChange,
        price = price,
        onChangePrice = viewModel::onPriceChange,
        description = description,
        onChangeDescription = viewModel::onDescriptionChange,
        status = status,
        onChangeStatus = viewModel::onStatusChange,
        onBack = {
            navController.navigateUp()
        },
        onSave = {
            viewModel.insertItem()
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
private fun AddItem(
    modifier: Modifier = Modifier,
    name: String,
    onChangeName: (String) -> Unit,
    price: String,
    onChangePrice: (String) -> Unit,
    description: String,
    onChangeDescription: (String) -> Unit,
    status: Boolean,
    onChangeStatus: (Boolean) -> Unit,
    onBack: () -> Unit = {},
    onSave: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "Add Item")
                },
                actions = {
                    TextButton(
                        onClick = onSave,
                        enabled = name.isNotEmpty() && price.isNotEmpty() && description.isNotEmpty()
                    ) {
                        Text(text = "Save")
                    }
                },
                navigationIcon = {
                    TextButton(
                        onClick = {
                            onBack()
                        },
                    ) {
                        Text(
                            text = "Back"
                        )
                    }
                }
            )
        },
        modifier = modifier
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 10.dp)
        ) {

            TextFieldComponent(
                value = name,
                onValueChange = onChangeName,
                placeholder = "Name",
                keyboardType = KeyboardType.Text
            )
            TextFieldComponent(
                value = price,
                onValueChange = { onChangePrice(it) },
                placeholder = "Price",
                keyboardType = KeyboardType.Number
            )
            TextFieldComponent(
                value = description,
                onValueChange = onChangeDescription,
                placeholder = "Description",
                keyboardType = KeyboardType.Text
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Is new product?")
                Checkbox(
                    checked = status,
                    onCheckedChange = onChangeStatus
                )
            }
        }
    }
}
