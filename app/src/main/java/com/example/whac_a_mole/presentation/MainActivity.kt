package com.example.whac_a_mole.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.whac_a_mole.common.Constants.gameScreenRoute
import com.example.whac_a_mole.common.Constants.resultScreenRoute
import com.example.whac_a_mole.common.Constants.startScreenRoute
import com.example.whac_a_mole.presentation.game_screen.GameScreen
import com.example.whac_a_mole.presentation.result_screen.ResultScreen
import com.example.whac_a_mole.presentation.start_screen.StartScreen
import com.example.whac_a_mole.presentation.theme.WhacAMoleTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WhacAMoleTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = startScreenRoute
                    ) {
                        composable(route = startScreenRoute) {
                            StartScreen(navController = navController)
                        }

                        composable(route = gameScreenRoute) {
                            GameScreen(navController = navController)
                        }

                        composable(
                            route = resultScreenRoute,
                        ) {
                            ResultScreen(navController = navController)
                        }
                    }


                }
            }
        }
    }
}

