package com.gladiator.BasketBuddy.view.composable


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

//@Preview(showBackground = true)
//@Composable
//fun Preview() {
//    val hint = "Search for groups..."
//
//    Column {
//        TopBar("Collaborations", onBackClick = {})
//        Collaborations(
//            hint = hint,
//            onSearch = { query ->
//                println("Searching for: $query")
//            }
//        )
//        Spacer(modifier = Modifier.height(500.dp))
//        BasketBuddyBottomNav()
//    }
//}

@Composable
fun Collaborations(
    hint: String,
    onSearch: (String) -> Unit,
    navController: NavController
) {
    var query by remember { mutableStateOf("") }

    OutlinedTextField(
        value = query,
        onValueChange = { query = it },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        placeholder = { Text(hint) },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search"
            )
        },
    )

    LaunchedEffect(query) {
        onSearch(query)
    }
}

fun onHomeClick(){}
fun onGroupClick(){}
fun onListClick(){}

