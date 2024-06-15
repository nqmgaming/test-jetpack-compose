package com.nqmgaming.test.presentation.home.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.nqmgaming.test.domain.model.Item

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ItemComponent(
    modifier: Modifier = Modifier,
    item: Item,
    onDelete: () -> Unit = {},
    onClick: () -> Unit = {},
    onDetail: () -> Unit = {}
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp, start = 16.dp, end = 16.dp)
            .combinedClickable(
                onClick = onClick
            ),
        colors = CardDefaults.cardColors(
            containerColor = if (item.ph31902Status == true) Color.White else Color.Gray
        )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
                    .weight(1f)
            ) {
                Text(
                    text = "Name: ${item.ph31902Name}",
                    style = MaterialTheme.typography.titleMedium.copy(
                        color = Color.Black,
                        fontWeight = FontWeight.SemiBold
                    )
                )
                Text(
                    text = "Price: ${item.ph31902Price}",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = Color.Black,
                        fontWeight = FontWeight.Normal
                    )
                )
                Text(
                    text = "Model: ${item.ph31902Model}",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = Color.Black,
                        fontWeight = FontWeight.Normal
                    ),
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = if (item.ph31902Status == true) "Status: New" else "Status: Old",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = Color.Black,
                        fontWeight = FontWeight.Normal
                    )
                )
            }
            Column {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = stringResource(id = android.R.string.ok),
                    tint = Color.Red,
                    modifier = Modifier
                        .padding(5.dp)
                        .combinedClickable(
                            onClick = onDelete
                        )
                )
                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = stringResource(id = android.R.string.ok),
                    tint = Color.Blue,
                    modifier = Modifier
                        .padding(5.dp)
                        .combinedClickable(
                            onClick = onDetail
                        )
                )
            }
        }
    }

}
