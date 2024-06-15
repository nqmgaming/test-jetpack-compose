package com.nqmgaming.test.presentation.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.nqmgaming.test.R
import com.nqmgaming.test.presentation.utils.Screen
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    Splash(
        onNavigate = {
            navController.navigate(Screen.Home.route) {
                popUpTo(Screen.Splash.route) {
                    inclusive = true
                }
            }
        }
    )
}

@Composable
private fun Splash(
    onNavigate: () -> Unit = {}
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            modifier = Modifier.size(200.dp)
        )
        Text(
            text = "Nguyen Quang Minh",
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            text = "PH31902",
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            text = "15/06/2024",
            style = MaterialTheme.typography.titleLarge
        )
        Button(
            onClick = onNavigate,
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text(text = "Go to Home")
        }
    }
}