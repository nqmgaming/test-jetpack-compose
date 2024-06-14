package com.nqmgaming.test.presentation.home

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CutCornerShape
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
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
        items = items,
        onNavigateToDetail = { item ->
            navController.navigate(Screen.Detail.route + "/${item.id}")
        }
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
private fun Home(
    modifier: Modifier = Modifier,
    items: List<Item> = emptyList(),
    onNavigateToDetail: (Item) -> Unit = {}
) {

    var showDialog by remember { mutableStateOf(false) }
    var showDetail by remember { mutableStateOf(false) }
    var selectedItem by remember { mutableStateOf<Item?>(null) }

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {
            Text(
                text = "Home Screen",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(16.dp)
            )
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(16.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(items.size) { index ->
                    Log.d("ItemComponent", "Item: ${items[index]}")
                    ItemComponent(
                        item = items[index],
                        onLongClick = {
                           onNavigateToDetail(items[index])
                        },
                        onClick = {
                            selectedItem = items[index]
                            showDetail = true
                        }
                    )
                }
            }
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
                    Column (
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        Text(
                            selectedItem?.title ?: "Image",
                            style = MaterialTheme.typography.titleMedium,
                            textAlign = TextAlign.Center
                        )
                        AsyncImage(
                            model = selectedItem?.thumbnailUrl ?: selectedItem?.url ?: "",
                            contentDescription = "Image",
                            modifier = Modifier
                                .background(
                                    shape = CutCornerShape(10.dp),
                                    color = Color.Black
                                )
                                .width(150.dp)
                                .height(150.dp)
                                .padding(5.dp),
                            contentScale = ContentScale.Crop,
                        )
                    }
                },
            )
        }
    }

}