package com.gladiator.BasketBuddy.view.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(title: String){
    TopAppBar(
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        },
        colors = TopAppBarDefaults.topAppBarColors()
    )
}
//@Preview(showBackground = true)
@Composable
fun HomeScreen(navController: NavController){


    var joinCode by remember { mutableStateOf("") }
    var groupName by remember { mutableStateOf("") }
    var generatedCode by remember { mutableStateOf("") }

    Scaffold(
        topBar = {TopBar("Home")},
        bottomBar = {BasketBuddyBottomNav(navController) }
    ) {paddingValues ->
        Column(
            modifier = Modifier.fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 20.dp, vertical = 12.dp)
                .verticalScroll(rememberScrollState()),
        ) {

            Text(
                text = "Groups",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.fillMaxWidth(),
                fontSize = 20.sp
            )

            Spacer(Modifier.height(24.dp))

            Text(
                text = "JOIN GROUP",
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(Modifier.height(8.dp))


            OutlinedTextField(
                value = joinCode,
                onValueChange = { joinCode = it },
                label = { Text("Enter code") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(Modifier.height(12.dp))

            Button (
                onClick = { navController.navigate("collaboration") {
                    launchSingleTop=true
                } },
                modifier = Modifier.fillMaxWidth()
            ){
                Text("Join Group")
            }

            Spacer(Modifier.height(32.dp))


            Text(
                text = "CREATE GROUP",
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(Modifier.height(8.dp))


            OutlinedTextField(
                value = groupName,
                onValueChange = { groupName = it },
                label = { Text("Enter Name") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(Modifier.height(12.dp))


            Button(
                onClick = {

                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Generate Code")
            }

            Spacer(Modifier.height(8.dp))


            if (generatedCode.isNotEmpty()) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = generatedCode,
                        style = MaterialTheme.typography.bodyLarge
                    )

                    IconButton(onClick = { /* share code */ }) {
                        Icon(
                            imageVector = Icons.Default.Share,
                            contentDescription = "Share"
                        )
                    }
                }
            }

            Spacer(Modifier.height(16.dp))


            Button(
                onClick = { navController.navigate("collaboration"){
                    launchSingleTop=true
                } },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Create Group")
            }

            Spacer(Modifier.weight(1f))

        }
    }
}