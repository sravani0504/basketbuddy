package com.gladiator.BasketBuddy.view.composable


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults

import androidx.compose.material3.Icon
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.gladiator.BasketBuddy.model.Group
import com.gladiator.BasketBuddy.viewmodel.CollaborationViewModel



@Composable
fun GroupList(viewModel: CollaborationViewModel= viewModel(),onItemClick: (Group) -> Unit){

    val groups by viewModel.groups.collectAsState()
    LazyColumn (modifier = Modifier.fillMaxSize().padding(10.dp)){
        items(items=groups,key={it.code}){group->
            Card(
                modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp, horizontal = 10.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                onClick = {}
            ) {
                Row (modifier = Modifier.padding(16.dp)) {
                    Text(text=group.code.toString(),
                        style = MaterialTheme.typography.bodyMedium)

                    Spacer(modifier = Modifier.width(16.dp))

                    Text(text = group.groupName,
                        style = MaterialTheme.typography.titleMedium)
                }
            }
        }
    }
}
@Composable
fun Collaborations(
    hint: String,
    onSearch: (String) -> Unit,
    navController: NavController,
    viewModel: CollaborationViewModel=viewModel()
) {

    Scaffold (
        topBar={TopBar("Collaborations", onBackClick = {navController.popBackStack()})},
        bottomBar = {BasketBuddyBottomNav(navController)}
    ){paddingValues ->
        Column (modifier = Modifier.padding(paddingValues)){
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
                }
            )

            LaunchedEffect(query) {
                onSearch(query)
            }
            GroupList(viewModel=viewModel, onItemClick = {})
        }
    }
}

