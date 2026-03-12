package com.gladiator.BasketBuddy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.gladiator.BasketBuddy.ui.theme.BasketBuddyTheme
import com.gladiator.BasketBuddy.view.composable.AddItemScreen
import com.gladiator.BasketBuddy.view.composable.AppNavGraph
import com.gladiator.BasketBuddy.view.composable.Collaborations
import com.gladiator.BasketBuddy.view.composable.HomeScreen
import com.gladiator.BasketBuddy.view.composable.LoginScreen
import com.gladiator.BasketBuddy.view.composable.SplashScreen
import com.gladiator.BasketBuddy.viewmodel.LoginAction

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BasketBuddyTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

//                    SplashScreen(onNavigate = { })
//                    Greeting(
//                        name = "Android",
//                        modifier = Modifier.padding(innerPadding)
//                    )
                    AppNavGraph(navController = rememberNavController())
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BasketBuddyTheme {
        Greeting("Android")
    }
}