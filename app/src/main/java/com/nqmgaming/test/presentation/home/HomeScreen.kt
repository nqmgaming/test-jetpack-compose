package com.nqmgaming.test.presentation.home

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.nqmgaming.test.domain.model.Item
import com.nqmgaming.test.presentation.home.components.AlertDialogComponent
import com.nqmgaming.test.presentation.home.components.ItemComponent
import com.nqmgaming.test.presentation.utils.Screen
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val coroutineScope = rememberCoroutineScope()

    val items = viewModel.items.value

    LaunchedEffect(key1 = items) {
        coroutineScope.launch {
            Log.d("HomeScreen", "Items: $items")
        }
    }

    Home(
        modifier = modifier.padding(horizontal = 16.dp),
        items = items.reversed().sortedBy { it.ph31902Status },
        onClick = {
            navController.navigate(Screen.AddItem.route)
        },
        onDelete = { item ->
            Log.d("HomeScreen", "Delete item: $item")
            viewModel.deleteItem(item)
        },
        onEdit = { item ->
            Log.d("HomeScreen", "Edit item: $item")
            navController.navigate(Screen.EditItem.route + "/${item.ph31902Id}")
        }
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
private fun Home(
    modifier: Modifier = Modifier,
    items: List<Item> = emptyList(),
    onClick: () -> Unit = {},
    onDelete: (Item) -> Unit = {},
    onEdit: (Item) -> Unit = {}
) {

    var showDialog by remember { mutableStateOf(false) }
    var showDetail by remember { mutableStateOf(false) }
    var selectedItem by remember { mutableStateOf<Item?>(null) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = onClick
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add todo item")
            }
        }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier.fillMaxSize()
            ) {
            Text(
                text = "List car",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(16.dp)
            )
            LazyColumn {

                item {
                    if (items.isEmpty()) {
                        CircularProgressIndicator()
                        Text("Loading...")
                    }
                }

                items(items.size) { index ->
                    ItemComponent(
                        item = items[index],
                        onDelete = {
                            selectedItem = items[index]
                            showDialog = true
                        },
                        onClick = {
                           onEdit(items[index])
                        },
                        onDetail = {
                            selectedItem = items[index]
                            showDetail = true
                        }
                    )
                }
            }
        }

        if (showDialog) {
            AlertDialogComponent(
                onDismiss = {
                    showDialog = false
                },
                onConfirm = {
                    showDialog = false
                    selectedItem?.let {
                        onDelete(it)
                    }
                },
                text = {
                    Text("Are you sure you want to delete this item?")
                },
                isDelete = true
            )
        }

        if (showDetail) {
            AlertDialogComponent(
                onDismiss = {
                    showDetail = false
                },
                onConfirm = {
                    showDetail = false
                },
                text = {
                    Column {
                        Text("Name: ${selectedItem?.ph31902Name}")
                        Text("Price: ${selectedItem?.ph31902Price}")
                        Text("Description: ${selectedItem?.ph31902Model}")
                        Text("Status: ${selectedItem?.ph31902Status}")
                    }
                },
                isDelete = false
            )
        }
    }

}