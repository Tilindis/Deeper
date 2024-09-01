package com.peak.deeper.feature.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.peak.deeper.feature.login.LoginScreen
import com.peak.deeper.feature.main.MainScreen
import com.peak.deeper.ui.theme.DeeperTheme

@Composable
fun NavHolder() {
    val navController = rememberNavController()

    DeeperTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            NavHost(
                navController = navController,
                startDestination = Screen.Login.route
            ) {
                composable(Screen.Login.route) { LoginScreen(navController = navController) }
                composable(Screen.Main.route) { MainScreen() }
            }
        }
    }
}
