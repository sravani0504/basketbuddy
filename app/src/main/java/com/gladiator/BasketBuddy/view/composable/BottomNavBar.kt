//package com.gladiator.BasketBuddy.view.composable
//
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.Groups
//import androidx.compose.material.icons.filled.Home
//import androidx.compose.material.icons.filled.Logout
//import androidx.compose.material3.Icon
//import androidx.compose.material3.NavigationBar
//import androidx.compose.material3.NavigationBarItem
//import androidx.compose.material3.NavigationBarItemDefaults
//import androidx.compose.material3.Scaffold
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.navigation.NavController
//import androidx.navigation.compose.rememberNavController
//
//@Composable
//fun BasketBuddyBottomNav(navController: NavController) {
//    var selectedIndex by remember { mutableStateOf(0) }
//
//    NavigationBar (
//        containerColor = Color(0xFFF5EBDD)
//    ){
//        NavigationBarItem(
//            selected = selectedIndex == 0,
//            onClick = {
//                selectedIndex = 0
//                navController.navigate("home") {
//                    popUpTo("home") { inclusive = true } // Prevents stack buildup
//                }
//            },
//            icon = { Icon(Icons.Filled.Home, contentDescription = "Home", modifier = Modifier.size(26.dp)) },
//            label = { Text("Home") },
//            colors = NavigationBarItemDefaults.colors(selectedIconColor = Color(0xFFB08968),
//                selectedTextColor = Color(0xFFB08968),
//                unselectedIconColor = Color.Gray,
//                unselectedTextColor = Color.Gray,
//                indicatorColor = Color(0xFFEAD8C0)
//            )
//        )
//        NavigationBarItem(
//            selected = selectedIndex == 1,
//            onClick = {
//                selectedIndex = 1
//                navController.navigate("collaboration"){
//                    popUpTo("collaboration"){ inclusive = true}
//                }
//            },
//            icon = { Icon(Icons.Filled.Groups, contentDescription = "Group", modifier = Modifier.size(26.dp))
//                   },
//            label = { Text("Group") },
//            colors = NavigationBarItemDefaults.colors(selectedIconColor = Color(0xFFB08968),
//                selectedTextColor = Color(0xFFB08968),
//                unselectedIconColor = Color.Gray,
//                unselectedTextColor = Color.Gray,
//                indicatorColor = Color(0xFFEAD8C0))
//        )
//
//        NavigationBarItem(
//            selected = selectedIndex == 2,
//            onClick = {
//                selectedIndex = 2
//                navController.navigate("login")
//            },
//            icon = { Icon(Icons.Default.Logout, contentDescription = "Logout") },
//            label = { Text("Logout") },
//            colors = NavigationBarItemDefaults.colors(selectedIconColor = Color(0xFFB08968),
//                selectedTextColor = Color(0xFFB08968),
//                unselectedIconColor = Color.Gray,
//                unselectedTextColor = Color.Gray,
//                indicatorColor = Color(0xFFEAD8C0)
//            )
//        )
//    }
//}
//
//
//@Preview(showBackground = true)
//@Composable
//fun BasketBuddyBottomNavPreview() {
//    val navController = rememberNavController()
//    Scaffold (
//        bottomBar = { BasketBuddyBottomNav(navController) }
//    ) { innerPadding ->
//
//        Text(text = "Preview Content", modifier = androidx.compose.ui.Modifier.padding(innerPadding))
//    }
//}
//

package com.gladiator.BasketBuddy.view.composable

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.gladiator.BasketBuddy.repo.GroupSession
import com.gladiator.BasketBuddy.repo.UserSession

@Composable
fun BasketBuddyBottomNav(navController: NavController) {
    var showLogoutDialog by remember { mutableStateOf(false) }
    //var selectedIndex by remember { mutableStateOf(0) }
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

    NavigationBar (
        modifier = Modifier.shadow(12.dp),
        containerColor = Color(0xFFF5EBDD)
    ){
        NavigationBarItem(
            //selected = selectedIndex == 0,
            selected = currentRoute == "home",
            onClick = {
                //selectedIndex = 0
                navController.navigate("home") {
                    popUpTo("home")
                    //{ inclusive = true } // Prevents stack buildup
                    launchSingleTop = true
                }
            },
            icon = { Icon(Icons.Filled.Home, contentDescription = "Home", modifier = Modifier.size(26.dp)) },
            label = { Text("Home") },
            colors = NavigationBarItemDefaults.colors(selectedIconColor = Color(0xFFB08968),
                selectedTextColor = Color(0xFFB08968),
                unselectedIconColor = Color.Gray,
                unselectedTextColor = Color.Gray,
                indicatorColor = Color(0xFFEAD8C0)
            )
        )
        NavigationBarItem(
           // selected = selectedIndex == 1,
            selected = currentRoute=="collaboration",
            onClick = {
               // selectedIndex = 1
                navController.navigate("collaboration"){
                    //popUpTo("collaboration"){ inclusive = true}
                    launchSingleTop=true

                }
            },
            icon = { Icon(Icons.Filled.Groups, contentDescription = "Group", modifier = Modifier.size(26.dp))
            },
            label = { Text("Group") },
            colors = NavigationBarItemDefaults.colors(selectedIconColor = Color(0xFFB08968),
                selectedTextColor = Color(0xFFB08968),
                unselectedIconColor = Color.Gray,
                unselectedTextColor = Color.Gray,
                indicatorColor = Color(0xFFEAD8C0))
        )

        NavigationBarItem(
            //selected = selectedIndex == 2,
            selected = currentRoute == "login",
            onClick = {
                //selectedIndex = 2
                //launchSingleTop = true
                showLogoutDialog=true
            },
            icon = { Icon(Icons.Default.Logout, contentDescription = "Logout") },
            label = { Text("Logout", color = Color.Red) },
            colors = NavigationBarItemDefaults.colors(selectedIconColor = Color(0xFFB08968),
                selectedTextColor = Color(0xFFB08968),
                unselectedIconColor = Color.Gray,
                unselectedTextColor = Color.Gray,
                indicatorColor = Color(0xFFEAD8C0)
            )
        )
    }
    if (showLogoutDialog) {
        AlertDialog(
            onDismissRequest = { showLogoutDialog = false },

            title = {
                Text("Logout")
            },

            text = {
                Text("Are you sure you want to logout?")
            },

            confirmButton = {
                TextButton(
                    onClick = {
                        showLogoutDialog = false
                        GroupSession.clear()
                        UserSession.clear()

                        navController.navigate("login") {
                            popUpTo("home") { inclusive = true }
                            launchSingleTop = true
                        }
                    }
                ) {
                    Text("Logout")
                }
            },

            dismissButton = {
                TextButton(
                    onClick = {
                        showLogoutDialog = false
                    }
                ) {
                    Text("Cancel")
                }
            }
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


