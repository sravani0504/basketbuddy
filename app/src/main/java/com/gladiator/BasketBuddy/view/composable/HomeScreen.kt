package com.gladiator.BasketBuddy.view.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.ShoppingBasket
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.gladiator.BasketBuddy.ui.theme.BasketBuddyTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(title: String){
    TopAppBar(
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.fillMaxWidth().padding(start = 140.dp),
//                textAlign = TextAlign.Center,
                color = Color(0xFF5A3E2B),
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFFF5EBDD),
            titleContentColor = Color(0xFF5A3E2B))
    )
}
@Composable
fun HomeScreen(navController: NavController) {


    var joinCode by remember { mutableStateOf("") }
    var groupName by remember { mutableStateOf("") }
    var generatedCode by remember { mutableStateOf("") }

    Scaffold(
        topBar = { TopBar("Home") },
        bottomBar = { BasketBuddyBottomNav(navController) }
    ) { paddingValues ->
        Column(
            modifier = Modifier.fillMaxSize()
                .background(Color(0xFFF5EBDD))
                .padding(paddingValues)
                .padding(horizontal = 24.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(24.dp))

            Icon(
                imageVector = Icons.Default.ShoppingBasket,
                contentDescription = "Basket",
                tint = Color(0xFFB08968),
                modifier = Modifier.size(70.dp)
            )

            Spacer(Modifier.height(24.dp))

            Text(
                text = "Groups",
                style = MaterialTheme.typography.headlineMedium,
                //modifier = Modifier.fillMaxWidth(),
                fontSize = 22.sp
            )

            Spacer(Modifier.height(24.dp))

            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(20.dp)) {

                    Text(
                        text = "Join Group",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(Modifier.height(12.dp))


                    OutlinedTextField(
                        value = joinCode,
                        onValueChange = { joinCode = it },
                        label = { Text("Enter Code") },
                        shape = RoundedCornerShape(14.dp),
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(Modifier.height(12.dp))

                    Button(
                        onClick = {
                            navController.navigate("collaboration") {
                                launchSingleTop = true
                            }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFB08968)),
                        shape = RoundedCornerShape(14.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Join Group")
                    }
                }
            }

            Spacer(Modifier.height(24.dp))

            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(20.dp)
                ) {
                    Text(
                        text = "Create Group",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(Modifier.height(12.dp))


                    OutlinedTextField(
                        value = groupName,
                        onValueChange = { groupName = it },
                        label = { Text("Enter Group Name") },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(14.dp)
                    )

                    Spacer(Modifier.height(12.dp))

                    Button(
                        onClick = {
                            generatedCode = "BB${(1000..9999).random()}"
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFB08968)
                        ),
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
                    Spacer(Modifier.height(12.dp))

                    Button(
                        onClick = {
                            navController.navigate("collaboration") {
                                launchSingleTop = true
                            }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFB08968)),
                        shape = RoundedCornerShape(14.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Create Group")
                    }
                }
            }

            Spacer(Modifier.height(40.dp))

        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview(){
    val navController = rememberNavController()

    BasketBuddyTheme {
        TopBar(title = "Home")
        HomeScreen(navController = navController)

    }
}