package com.peak.deeper.feature.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.peak.deeper.feature.navigation.Screen
import com.peak.deeper.ui.theme.DeeperTheme

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    navController: NavController
) {
    val state = viewModel.state.collectAsState().value

    state.isUserLogged?.let { isUserLogged ->
        if (isUserLogged) {
            navigateToMainScreen(navController)
        } else {
            LoginContent(
                state = state,
                onEmail = viewModel::updateEmail,
                onPassword = viewModel::updatePassword,
                onLogin = {
                    viewModel.onLogin()
                }
            )
        }
    }
}

private fun navigateToMainScreen(navController: NavController) {
    navController.navigate(Screen.Main.route)
}

@Preview(showBackground = true)
@Composable
fun PreviewLoginScreen() {
    val navController = rememberNavController()
    DeeperTheme {
        LoginScreen(navController = navController)
    }
}
