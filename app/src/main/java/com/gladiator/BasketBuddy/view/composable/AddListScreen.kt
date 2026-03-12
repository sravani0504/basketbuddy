package com.gladiator.BasketBuddy.view.composable

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

//@Preview(showBackground = true)
@Composable
fun AddListScreen(navController: NavController) {
    var listName by remember { mutableStateOf("") }

    Scaffold(
        topBar = { TopBar("Add List", onBackClick = {}) },
        bottomBar = { BasketBuddyBottomNav() },
        containerColor = MaterialTheme.colorScheme.background
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Create New List",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.align(Alignment.Start)
            )

            OutlinedTextField(
                value = listName,
                onValueChange = { listName = it },
                label = { Text("List Name") },
                placeholder = { Text("e.g. Weekly Grocery") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                singleLine = true
            )

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = { navController.navigate("addItem")},
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(16.dp),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp)
            ) {
                Icon(Icons.Default.Add, contentDescription = null)
                Spacer(Modifier.width(8.dp))
                Text(
                    text = "ADD LIST",
                    style = MaterialTheme.typography.titleMedium.copy(letterSpacing = 1.sp)
                )
            }
        }
    }
}
