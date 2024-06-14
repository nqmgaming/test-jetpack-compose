package com.nqmgaming.test.presentation.home.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlertDialogComponent(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
    text: @Composable () -> Unit,
) {
    AlertDialog(
        icon = {
                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = stringResource(id = android.R.string.ok),
                    tint = Color.Blue
                )
        },
        title = {
            Text(
                text = "Item Detail",
                textAlign = TextAlign.Center
            )
        },
        text = {
            text()
        },
        onDismissRequest = {
            onDismiss()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirm()
                }
            ) {
                Text(
                    text = "Ok",
                    color = Color.Black
                )
            }
        },
        containerColor = Color.White
    )
}