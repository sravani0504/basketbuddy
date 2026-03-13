package com.gladiator.BasketBuddy.view.composable

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.gladiator.BasketBuddy.model.Screen
import com.gladiator.BasketBuddy.viewmodel.AddListViewModel

//@Preview(showBackground = true)
@Composable
fun AddListScreen(
    navController: NavController,
    viewModel: AddListViewModel = viewModel()
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = { TopBar("Add List", onBackClick = {navController.popBackStack()}) },
        bottomBar = { BasketBuddyBottomNav(navController) },
        containerColor = Color(0xFFF5EBDD)
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
                fontWeight = FontWeight.Bold,
                color = Color(0xFF5A3E2B),
                modifier = Modifier.align(Alignment.Start)
            )

            if (uiState.selectedGroupName.isNotBlank()) {
                Text(
                    text = "Group: ${uiState.selectedGroupName}",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color(0xFF7A5C45),
                    modifier = Modifier.align(Alignment.Start)
                )
            }

            OutlinedTextField(
                value = uiState.listName,
                onValueChange = viewModel::onListNameChanged,
                label = { Text("List Name") },
                placeholder = { Text("e.g. Weekly Grocery") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(14.dp),
                singleLine = true,
                isError = uiState.listNameError != null,
                supportingText = {
                    uiState.listNameError?.let {
                        Text(it)
                    }
                }
            )

            uiState.message?.let {
                Text(
                    text = it,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.align(Alignment.Start)
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = {
                    viewModel.createList {
                        navController.navigate(Screen.ItemDisplay.route) {
                            launchSingleTop = true
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFB08968))
            ) {
                Icon(Icons.Default.Add, contentDescription = null, tint = Color.White)
                Spacer(Modifier.width(8.dp))
                Text(
                    text = "ADD LIST",
                    style = MaterialTheme.typography.titleMedium.copy(letterSpacing = 1.sp),
                    color = Color.White
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAddList() {
    val navController = rememberNavController()
    AddListScreen(navController)
}