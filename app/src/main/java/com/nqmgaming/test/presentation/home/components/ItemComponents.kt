package com.nqmgaming.test.presentation.home.components

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.nqmgaming.test.domain.model.Item

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ItemComponent(
    modifier: Modifier = Modifier,
    item: Item,
    onLongClick: () -> Unit = {},
    onClick: () -> Unit = {}
) {
    Log.d("ItemComponent", "Item: $item")
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp, start = 16.dp, end = 16.dp)
            .combinedClickable(
                onLongClick = onLongClick,
                onClick = onClick
            ),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(5.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = item.thumbnailUrl ?: item.url ?: "",
                contentDescription = "Image",
                modifier = Modifier
                    .fillMaxSize()
                    .padding(5.dp),
                contentScale = ContentScale.FillWidth,
            )
            Text(
                text = "Title: ${item.title}",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color.Black,
                    fontWeight = FontWeight.Normal
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

        }
    }

}
