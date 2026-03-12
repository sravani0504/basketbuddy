package com.gladiator.BasketBuddy.view.composable

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun BasketBuddyBottomNav(navController: NavController) {
    var selectedIndex by remember { mutableStateOf(0) }

    NavigationBar {
        NavigationBarItem(
            selected = selectedIndex == 0,
            onClick = {
                selectedIndex = 0
                navController.navigate("home") {
                    popUpTo("home") { inclusive = true } // Prevents stack buildup
                }
            },
            icon = { Icon(Icons.Filled.Home, contentDescription = "Home") },
            label = { Text("Home") }
        )

        NavigationBarItem(
            selected = selectedIndex == 1,
            onClick = {
                selectedIndex = 1
                navController.navigate("collaboration")
            },
            icon = { Icon(Icons.Filled.Groups, contentDescription = "Group") },
            label = { Text("Group") }
        )

        NavigationBarItem(
            selected = selectedIndex == 2,
            onClick = {
                selectedIndex = 2
                navController.navigate("login")
            },
            icon = { Icon(Icons.Default.Logout, contentDescription = "List") },
            label = { Text("Logout") }
        )
    }
}


@Preview(showBackground = true)
@Composable
fun BasketBuddyBottomNavPreview() {
    val navController = rememberNavController()
    Scaffold (
        bottomBar = { BasketBuddyBottomNav(navController) }
    ) { innerPadding ->

        Text(text = "Preview Content", modifier = androidx.compose.ui.Modifier.padding(innerPadding))
    }
}

