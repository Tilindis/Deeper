package com.peak.deeper.feature.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.peak.deeper.R
import com.peak.deeper.utils.constants.Constants.PASSWORD_HIDE_CHAR

@Composable
fun LoginContent(
    state: LoginState,
    onEmail: (String) -> Unit,
    onPassword: (String) -> Unit,
    onLogin: () -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_logo_deeper),
            contentDescription = stringResource(id = R.string.login_image_content_description),
            modifier = Modifier
                .size(136.dp)
                .padding(16.dp)
        )

        OutlinedTextField(
            value = state.email,
            onValueChange = { onEmail(it) },
            label = { Text(text = stringResource(id = R.string.login_label_email_or_username)) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
        )

        OutlinedTextField(
            value = state.password,
            onValueChange = { onPassword(it) },
            label = { Text(text = stringResource(id = R.string.login_label_password)) },
            visualTransformation = PasswordVisualTransformation(mask = PASSWORD_HIDE_CHAR),
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
        )

        Spacer(modifier = Modifier.height(4.dp))

        Button(
            onClick = { onLogin() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(text = stringResource(id = R.string.login_button_login_text))
        }

        TextButton(
            onClick = {
                throw RuntimeException("Test Crash") // Secret test crash :)
            }
        ) {
            Text(text = stringResource(id = R.string.login_button_forgot_password_text))
        }
    }
}
