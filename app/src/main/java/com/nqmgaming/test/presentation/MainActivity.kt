package com.nqmgaming.test.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.nqmgaming.test.presentation.detail.AddItemScreen
import com.nqmgaming.test.presentation.home.HomeScreen
import com.nqmgaming.test.presentation.splash.SplashScreen
import com.nqmgaming.test.presentation.utils.Screen
import com.nqmgaming.test.ui.theme.TestTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TestTheme {
                val navController = rememberNavController()
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = Screen.Splash.route,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable(
                            route = Screen.Splash.route
                        ) {
                            SplashScreen(navController = navController)
                        }
                        composable(
                            route = Screen.Home.route
                        ) {
                            HomeScreen(navController = navController)
                        }
                        composable(
                            route = Screen.Detail.route + "/{itemId}",
                            arguments = listOf(
                                navArgument(
                                    name = "itemId"
                                ) {
                                    type = NavType.IntType

                                }
                            )
                        ) {

                            val itemId = it.arguments?.getInt("itemId")

                            AddItemScreen(
                                navController = navController,
                            )
                        }

                    }
                }
            }
        }
    }
}
