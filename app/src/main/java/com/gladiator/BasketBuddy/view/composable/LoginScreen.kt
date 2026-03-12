package com.gladiator.BasketBuddy.view.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingBasket
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.gladiator.BasketBuddy.viewmodel.LoginAction
import com.gladiator.BasketBuddy.viewmodel.LoginViewModel

@Composable
fun LoginScreen(navController: NavController,viewModel: LoginViewModel,onLoginSuccess: () -> Unit = {},modifier: Modifier= Modifier){

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    var passwordVisible by remember { mutableStateOf(false) }

//    Scaffold (
//        topBar = {SingleTopBar("Login")}
//    ){ paddingValues ->

        Column(modifier = Modifier.fillMaxSize().background(Color(0xFFF5EBDD))
            .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            //verticalArrangement = Arrangement.Top
        ) {

            Spacer(modifier = Modifier.height(40.dp))

            Icon(
                imageVector = Icons.Default.ShoppingBasket,
                contentDescription = "Basket",
                tint = Color(0xFFB08968),
                modifier = Modifier.size(70.dp)
            )
            Spacer(
                modifier = Modifier.height(30.dp)
            )
            Text(
                "Welcome Back",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF5A3E2B)
            )

            Spacer(modifier = Modifier.height(30.dp))

            //Username
            OutlinedTextField(
                value = uiState.username,
                onValueChange = {viewModel.onAction(LoginAction.UsernameChanged(it))},
                label = {Text("Username")},
                leadingIcon = {
                    Icon(Icons.Default.Person, contentDescription = null)
                },
                shape = RoundedCornerShape(14.dp),
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
                modifier= Modifier.fillMaxWidth().padding(10.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            //Password
            OutlinedTextField(
                value = uiState.password,
                onValueChange = { viewModel.onAction(LoginAction.PasswordChanged(it)) },
                label = { Text("Password") },
                leadingIcon = {Icon(Icons.Default.Lock, contentDescription = null)},
                //singleLine = true,
                shape = RoundedCornerShape(12.dp),
                visualTransformation = if (passwordVisible) VisualTransformation.None
                else PasswordVisualTransformation(),
                trailingIcon = {
                    IconButton(
                        onClick = {passwordVisible = !passwordVisible}
                    ) {
                        Icon(
                            imageVector = if (passwordVisible)
                            Icons.Default.Visibility
                            else Icons.Default.VisibilityOff,
                            contentDescription = null
                        )
                    }
//                    TextButton(
//                        onClick = { passwordVisible = !passwordVisible },
//                        contentPadding = PaddingValues(0.dp)
//                    ) { Text(if (passwordVisible) "Hide" else "Show") }
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
                singleLine = true,
                modifier = Modifier.fillMaxWidth().padding(10.dp)
            )
            Spacer(modifier = Modifier.height(5.dp))

            //Login

            Button(
                enabled = uiState.isFormValid && !uiState.isLoading,
                onClick = {
                    viewModel.onAction(LoginAction.Submit(onSuccess = onLoginSuccess))
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFB08968)),
                shape = RoundedCornerShape(14.dp),
                modifier= Modifier.fillMaxWidth().padding(52.dp)
            ) {
                if (uiState.isLoading){
                    CircularProgressIndicator(
                        modifier= Modifier.size(20.dp),
                        strokeWidth = 2.dp,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                } else{
                    Text(
                        "Login",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
            Spacer(modifier = Modifier.height(40.dp))
        }
    }

@Preview(showBackground = true)
@Composable
private fun LoginScreenPreview(){
    val navController = rememberNavController()
    LoginScreen(
        navController = navController,
        viewModel = LoginViewModel(),
        onLoginSuccess = { }
    )

}