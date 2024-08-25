package com.peak.deeper.feature.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.peak.deeper.ui.theme.DeeperTheme

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
) {
    val state = viewModel.state.collectAsState().value

    LoginContent(
        state = state,
        onEmail = viewModel::updateEmail,
        onPassword = viewModel::updatePassword
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewLoginScreen() {
    DeeperTheme {
        LoginScreen()
    }
}
