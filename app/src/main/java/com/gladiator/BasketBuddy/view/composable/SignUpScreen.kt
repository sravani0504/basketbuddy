package com.gladiator.BasketBuddy.view.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material.icons.filled.Email
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.gladiator.BasketBuddy.viewmodel.SignUpAction
import com.gladiator.BasketBuddy.viewmodel.SignUpViewModel


@Composable
fun SignUpScreen(navController: NavController,
    viewModel: SignUpViewModel,
    onSignUpSuccess: () -> Unit = {}
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    var passwordVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5EBDD))
            .verticalScroll(rememberScrollState())
            .padding(24.dp),

        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(40.dp))

        Icon(
            imageVector = Icons.Default.ShoppingBasket,
            contentDescription = "Basket",
            tint = Color(0xFFB08968),
            modifier = Modifier.size(70.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Basket Buddy",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF5A3E2B),
            letterSpacing = 1.sp
        )

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = "Create Account",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF5A3E2B)
        )

        Spacer(modifier = Modifier.height(30.dp))

        //EMAIL
        OutlinedTextField(
            value = uiState.email,
            onValueChange = {
                viewModel.onAction(SignUpAction.EmailChanged(it))
            },
            leadingIcon = {
                Icon(Icons.Default.Email, contentDescription = null)
            },
            label = { Text("Email") },
            isError = uiState.emailError != null,
            supportingText = {
                uiState.emailError?.let {
                    Text(it)
                }
            },
            shape = RoundedCornerShape(14.dp),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))


        //USERNAME
        OutlinedTextField(
            value = uiState.username,
            onValueChange = {
                viewModel.onAction(SignUpAction.UsernameChanged(it))
            },
            leadingIcon = {
                Icon(Icons.Default.Person, contentDescription = null)
            },
            label = { Text("Username") },
            isError = uiState.usernameError != null,
            supportingText = {
                uiState.usernameError?.let {
                    Text(it)
                }
            },
            shape = RoundedCornerShape(14.dp),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        //PASSWORD
        OutlinedTextField(
            value = uiState.password,
            onValueChange = {
                viewModel.onAction(SignUpAction.PasswordChanged(it))
            },
            leadingIcon = {
                Icon(Icons.Default.Lock, contentDescription = null)
            },
            trailingIcon = {

                IconButton(
                    onClick = { passwordVisible = !passwordVisible }
                ) {

                    Icon(
                        imageVector =
                            if (passwordVisible)
                                Icons.Default.Visibility
                            else
                                Icons.Default.VisibilityOff,
                        contentDescription = null
                    )
                }
            },

            visualTransformation =
                if (passwordVisible)
                    VisualTransformation.None
                else
                    PasswordVisualTransformation(),

            label = { Text("Password") },
            isError = uiState.passwordError != null,
            supportingText = {
                uiState.passwordError?.let {
                    Text(it)
                }
            },
            shape = RoundedCornerShape(14.dp),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        //SIGNUP BUTTON
        Button(
            onClick = {
                viewModel.onAction(
                    SignUpAction.Submit(
                        onSuccess = onSignUpSuccess
                    )
                )
            },

            enabled = uiState.isFormValid,

            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFB08968)
            ),

            shape = RoundedCornerShape(14.dp),

            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)

        ) {

            if (uiState.isLoading) {

                CircularProgressIndicator(
                    color = Color.White,
                    strokeWidth = 2.dp,
                    modifier = Modifier.size(20.dp)
                )

            } else {

                Text(
                    text = "Sign Up",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )

            }
        }

        Spacer(modifier = Modifier.height(18.dp))

        Row {
            Text(
                text = "Already a user? ",
                color = Color.Gray
            )
            Text(
                text = "Log in",
                color = Color(0xFFB08968),
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.height(40.dp))
    }
}


@Preview(showBackground = true)
@Composable
fun SignUpScreenPreview() {
//
//        SignUpScreen(
//            viewModel  = SignUpViewModel(),
//            onSignUpSuccess = { }
//      )
//
//}
}
