package com.gladiator.BasketBuddy.view.composable

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.gladiator.BasketBuddy.viewmodel.AddItemViewModel

@Composable
fun AddItemScreen(
    navController: NavController,
    viewModel: AddItemViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val context = LocalContext.current

    LaunchedEffect(uiState.message) {
        uiState.message?.let {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }
    }

    Scaffold(
        topBar = { TopBar("Add Item", onBackClick = { navController.popBackStack() }) },
        bottomBar = { BasketBuddyBottomNav(navController) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF5EBDD))
                .padding(paddingValues)
                .padding(24.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Enter Item Details",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF5A3E2B),
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(bottom = 24.dp)
            )

            if (uiState.selectedListName.isNotBlank()) {
                Text(
                    text = "List: ${uiState.selectedListName}",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color(0xFF7A5C45),
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(bottom = 12.dp)
                )
            }

            OutlinedTextField(
                value = uiState.itemName,
                onValueChange = viewModel::onItemNameChanged,
                label = { Text("Item Name") },
                placeholder = { Text("e.g. Milk") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(14.dp),
                singleLine = true,
                isError = uiState.itemNameError != null,
                supportingText = {
                    uiState.itemNameError?.let {
                        Text(it)
                    }
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = uiState.itemDescription,
                onValueChange = viewModel::onItemDescriptionChanged,
                label = { Text("Description") },
                placeholder = { Text("e.g. 1 litre packet") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(14.dp)
            )

            Spacer(modifier = Modifier.height(14.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {

                OutlinedTextField(
                    value = uiState.quantityText,
                    onValueChange = viewModel::onQuantityChanged,
                    label = { Text("Quantity") },
                    placeholder = { Text("1") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(14.dp),
                    singleLine = true,
                    isError = uiState.quantityError != null,
                    supportingText = {
                        uiState.quantityError?.let {
                            Text(it)
                        }
                    }
                )
                Spacer(modifier = Modifier.weight(1f))
            }

            uiState.message?.let {
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = it,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.align(Alignment.Start)
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = {
                    viewModel.saveItem {
                        navController.popBackStack()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(Color(0xFFB08968)),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp)
            ) {
                Icon(Icons.Default.Add, contentDescription = null, tint = Color.White)
                Spacer(Modifier.width(8.dp))
                Text("ADD ITEM", style = MaterialTheme.typography.titleMedium, color = Color.White)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAddItem() {
    val navController = rememberNavController()
    AddItemScreen(navController)
}
