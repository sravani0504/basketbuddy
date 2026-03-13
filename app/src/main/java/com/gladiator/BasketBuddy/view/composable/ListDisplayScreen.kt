package com.gladiator.BasketBuddy.view.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons

import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.gladiator.BasketBuddy.R
import androidx.lifecycle.viewmodel.compose.viewModel
import com.gladiator.BasketBuddy.model.ItemList
import com.gladiator.BasketBuddy.model.Screen
import com.gladiator.BasketBuddy.viewmodel.BasketViewModel


@Composable
fun ListScreen(
    navController: NavController,
    viewModel: BasketViewModel = viewModel()
) {
    val hint = "Search lists..."
    val lists by viewModel.lists.collectAsState()
    val selectedGroupName by viewModel.selectedGroupName.collectAsState()
    val message by viewModel.message.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadListsForSelectedGroup()
    }

    Scaffold (
        topBar={
            TopBar(
                "Lists",
                onBackClick = {navController.popBackStack()})},
        bottomBar={BasketBuddyBottomNav(navController)}
    ){paddingValues ->
        Column(Modifier.fillMaxSize()
            .background(Color(0xFFF5EBDD))
            .padding(paddingValues))
        {
            if (selectedGroupName.isNotBlank()) {
                Text(
                    text = "Group: $selectedGroupName",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color(0xFF5A3E2B),
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                )
            }

            ListDisplayScreen(
                hint = hint,
                onSearch = { message ->
                    viewModel.onSearch(message)
                }
            )

            message?.let {
                Text(
                    text = it,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }

            ListsContent(
                lists = lists,
                onListClick = { itemList ->
                    viewModel.onListSelected(itemList)
                    navController.navigate(Screen.ItemDisplay.route) {
                        launchSingleTop = true
                    }
                }
            )

            Spacer(Modifier.weight(1f))

            Row (
                modifier = Modifier.fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.End
            ){
                IconButton(onClick = {navController.navigate("addList")}) {
                    Icon(
                        painter = painterResource(id = R.drawable.outline_playlist_add_24),
                        contentDescription = "Add List",
                        modifier = Modifier.size(50.dp),
                        tint = Color(0xFFB08968)
                    )
                }
            }
        }
    }
}

@Composable
private fun ListsContent(
    lists: List<ItemList>,
    onListClick: (ItemList) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        items(lists, key = { it.listId }) { itemList ->
            Card(
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp)
                    .clickable { onListClick(itemList) }
            ) {
                Text(
                    text = itemList.name,
                    style = MaterialTheme.typography.titleMedium,
                    color = Color(0xFF5A3E2B),
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}

@Composable
fun ListDisplayScreen(
    hint: String,
    onSearch: (String) -> Unit
) {
    var message by remember { mutableStateOf("") }

    OutlinedTextField(
        value = message,
        onValueChange = { message = it },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        placeholder = { Text(hint) },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search",
                tint = Color(0xFFB08968)
            )
        },
        shape = RoundedCornerShape(14.dp)
    )

    LaunchedEffect(message) { onSearch(message) }
}



@Preview(showBackground = true)
@Composable
fun PreviewList() {
    val hint = "rrrrrrr....."
    var navController = rememberNavController()

    ListScreen(navController)

}

