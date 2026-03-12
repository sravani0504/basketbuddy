package com.gladiator.BasketBuddy.view.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.gladiator.BasketBuddy.viewmodel.LoginAction
import com.gladiator.BasketBuddy.viewmodel.LoginViewModel

@Composable
fun LoginScreen(navController: NavController,viewModel: LoginViewModel,onLoginSuccess: () -> Unit = {},modifier: Modifier= Modifier){

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold (
        topBar = {SingleTopBar("Login")}
    ){paddingValues ->
        Column(modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top) {

            Spacer(modifier = Modifier.height(24.dp))

            OutlinedTextField(
                value = uiState.username,
                onValueChange = {viewModel.onAction(LoginAction.UsernameChanged(it))},
                label = {Text("Username")},
                shape = RoundedCornerShape(12.dp),
                isError = uiState.usernameError!=null,
                supportingText = {
                    uiState.usernameError?.let {error->
                        Text(
                            text=error,
                            color=MaterialTheme.colorScheme.error
                        )
                    }
                },
                singleLine = true,
                modifier= Modifier.fillMaxWidth().padding(paddingValues).padding(10.dp)
            )


            var passwordVisible by remember { mutableStateOf(false) }
            OutlinedTextField(
                value = uiState.password,
                onValueChange = { viewModel.onAction(LoginAction.PasswordChanged(it)) },
                label = { Text("Password") },
                singleLine = true,
                shape = RoundedCornerShape(12.dp),
                visualTransformation = if (passwordVisible) VisualTransformation.None
                else PasswordVisualTransformation(),
                trailingIcon = {
                    TextButton(
                        onClick = { passwordVisible = !passwordVisible },
                        contentPadding = PaddingValues(0.dp)
                    ) { Text(if (passwordVisible) "Hide" else "Show") }
                },

                isError = uiState.passwordError != null,
                supportingText = {
                    uiState.passwordError?.let { error ->
                        Text(
                            text = error,
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth().padding(10.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                enabled = uiState.isFormValid && !uiState.isLoading,
                onClick = {
                    viewModel.onAction(LoginAction.Submit(onSuccess = onLoginSuccess))
                },modifier= Modifier.fillMaxWidth().padding(10.dp)
            ) {
                if (uiState.isLoading){
                    CircularProgressIndicator(
                        modifier= Modifier.size(20.dp),
                        strokeWidth = 2.dp,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
                Text("Login")
            }
        }
    }

}

//@Preview(showBackground = true)
//@Composable
//private fun LoginScreenPreview(){
//    LoginScreen(viewModel())
//
//}